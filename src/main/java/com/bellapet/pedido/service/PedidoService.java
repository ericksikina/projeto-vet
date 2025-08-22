package com.bellapet.pedido.service;

import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.carrinho.service.CarrinhoService;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.service.ClienteService;
import com.bellapet.pagamento.http.adapter.PagamentoAdapter;
import com.bellapet.pagamento.http.request.PagamentoRequest;
import com.bellapet.pagamento.persistence.model.Pagamento;
import com.bellapet.pedido.http.adapter.PedidoAdapter;
import com.bellapet.pedido.http.request.StatusPedidoRequest;
import com.bellapet.pedido.http.response.ResumoPedidoResponse;
import com.bellapet.pedido.http.response.PedidoResponse;
import com.bellapet.pedido.persistence.entity.Pedido;
import com.bellapet.pedido.persistence.entity.enums.StatusPedido;
import com.bellapet.pedido.persistence.repository.PedidoRepository;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produto.service.ProdutoService;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import com.bellapet.produtoPedido.persistence.entity.ProdutoPedido;
import com.bellapet.produtoPedido.persistence.repository.ProdutoPedidoRepository;
import com.bellapet.utils.service.EmailService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CarrinhoService carrinhoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EmailService emailService;
    private final ProdutoPedidoRepository produtoPedidoRepository;

    public List<ResumoPedidoResponse> listarPedidos(HttpServletRequest httpServletRequest) {
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        return PedidoAdapter.toResumoResponseList(this.pedidoRepository.findAllByCliente(cliente));
    }

    public PedidoResponse buscarPedido(Long idPedido) {
        return PedidoAdapter.toResponse(this.buscarPedidoPorId(idPedido));
    }

    @Transactional
    public void efetuarPedido(HttpServletRequest httpServletRequest, PagamentoRequest pagamentoRequest) {
        Carrinho carrinho = this.carrinhoService.buscarCarrinhoPorCliente(httpServletRequest);
        Pagamento pagamento = PagamentoAdapter.toPagamento(new Pagamento(), pagamentoRequest);
        Pedido pedido = PedidoAdapter.toEntity(new Pedido(), pagamento, carrinho.getCliente());
        this.pedidoRepository.save(pedido);
        this.adicionarListaDePedidos(carrinho.getListaDeProdutos(), pedido);
        this.carrinhoService.esvaziarCarrinho(httpServletRequest);
    }

    @Transactional
    public void cancelarPedido(Long idPedido) {
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        this.podeSerCancelado(pedido.getStatusPedido());
        pedido.setStatusPedido(StatusPedido.CANCELADO);

        this.pedidoRepository.save(pedido);

        String mensagem = "Seu pedido " + pedido.getStatusPedido().getDescricao();
        String assunto = "Atualização do seu pedido - Bellapet";
        this.emailService.enviarEmailTexto(pedido.getCliente().getEmail(), mensagem, assunto);
    }

    @Transactional
    public void alterarStatusDoPedido(Long idPedido, StatusPedidoRequest statusPedidoRequest) {
        Pedido pedido = this.buscarPedidoPorId(idPedido);
        if(statusPedidoRequest.status().equals(StatusPedido.CANCELADO))
            throw new IllegalArgumentException("O pedido não pode ser cancelado!");

        pedido.setStatusPedido(statusPedidoRequest.status());
        String mensagem = "Seu pedido " + statusPedidoRequest.status().getDescricao();
        String assunto = "Atualização do seu pedido - Bellapet";
        this.emailService.enviarEmailTexto(pedido.getCliente().getEmail(), mensagem, assunto);
        this.pedidoRepository.save(pedido);
    }

    private Pedido buscarPedidoPorId(Long id) {
        return this.pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));
    }

    private void podeSerCancelado(StatusPedido statusPedido) {
        if (!statusPedido.equals(StatusPedido.REALIZADO)) {
            throw new IllegalArgumentException("O pedido não pode ser cancelado pois já " + statusPedido.getDescricao() + "!");
        }
    }

    private void adicionarListaDePedidos(List<ProdutoCarrinho> listaDeProdutoCarrinho, Pedido pedido) {
        if(listaDeProdutoCarrinho.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum produto no carrinho!");
        }

        List<ProdutoPedido> listaDeProdutoPedido = listaDeProdutoCarrinho.stream()
                .map(produtoCarrinho -> {
                    this.verificarEstoqueDisponivel(produtoCarrinho.getProduto(), produtoCarrinho.getQtde());
                    this.produtoService.reduzirQtdeEstoque(produtoCarrinho.getProduto(), produtoCarrinho.getQtde());
                    ProdutoPedido produtoPedido = new ProdutoPedido(produtoCarrinho.getQtde(), produtoCarrinho.getProduto(), pedido);
                    return this.produtoPedidoRepository.save(produtoPedido);
                })
                .toList();

        pedido.getListaDeProdutos().addAll(listaDeProdutoPedido);
        pedido.setTotal(this.calcularTotal(pedido.getListaDeProdutos()));
        this.pedidoRepository.save(pedido);
    }

    private BigDecimal calcularTotal(List<ProdutoPedido> listaProdutoPedido) {
        BigDecimal total = BigDecimal.ZERO;
        for (ProdutoPedido produtoPedido : listaProdutoPedido) {
            BigDecimal preco = produtoPedido.getProduto().getPreco();
            int quantidade = produtoPedido.getQtde();
            total = total.add(preco.multiply(BigDecimal.valueOf(quantidade)));
        }
        return total;
    }

    private void verificarEstoqueDisponivel(Produto produto, int qtde) {
        if(produto.getQtdeEstoque() < qtde)
            throw new IllegalArgumentException(produto.getNome() + " só tem " + qtde + " unidades em estoque!");
    }
}
