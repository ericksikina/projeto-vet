package com.bellapet.carrinho.service;

import com.bellapet.carrinho.http.adapter.CarrinhoAdapter;
import com.bellapet.carrinho.http.request.ProdutoCarrinhoRequest;
import com.bellapet.carrinho.http.response.CarrinhoResponse;
import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.carrinho.persistence.repository.CarrinhoRepository;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.service.ClienteService;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produto.service.ProdutoService;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import com.bellapet.produtoCarrinho.persistence.repository.ProdutoCarrinhoRepository;
import com.bellapet.produtoCarrinho.service.ProdutoCarrinhoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final ProdutoCarrinhoService produtoCarrinhoService;
    private final ProdutoCarrinhoRepository produtoCarrinhoRepository;

    public CarrinhoResponse buscarCarrinho(HttpServletRequest httpServletRequest) {
        return CarrinhoAdapter.toResponse(this.buscarCarrinhoPorCliente(httpServletRequest));
    }

    @Transactional
    public CarrinhoResponse adicionarProdutoCarrinho(HttpServletRequest httpServletRequest,
                                             ProdutoCarrinhoRequest produtoCarrinhoRequest){
        Carrinho carrinho = this.buscarCarrinhoPorCliente(httpServletRequest);
        Produto produto = this.produtoService.buscarProdutoPorId(produtoCarrinhoRequest.idProduto());
        Optional<ProdutoCarrinho> optionalProdutoCarrinho = this.produtoCarrinhoRepository
                .findByProdutoAndCarrinho(produto, carrinho);

        if(optionalProdutoCarrinho.isPresent()){
            ProdutoCarrinho produtoCarrinho = optionalProdutoCarrinho.get();
            produtoCarrinho.setQtde(produtoCarrinho.getQtde() + produtoCarrinhoRequest.qtde());
            this.verificarEstoqueDisponivel(produto, produtoCarrinho.getQtde());
            this.produtoCarrinhoRepository.save(produtoCarrinho);
        } else {
            this.verificarEstoqueDisponivel(produto, produtoCarrinhoRequest.qtde());
            ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(produtoCarrinhoRequest.qtde(), produto, carrinho);
            this.produtoCarrinhoRepository.save(produtoCarrinho);
            carrinho.getListaDeProdutos().add(produtoCarrinho);
        }

        carrinho.setTotal(this.calcularTotal(carrinho.getListaDeProdutos()));

        return CarrinhoAdapter.toResponse(this.carrinhoRepository.save(carrinho));
    }

    @Transactional
    public CarrinhoResponse removerProdutoCarrinho(HttpServletRequest httpServletRequest,
                                                   ProdutoCarrinhoRequest produtoCarrinhoRequest){
        Carrinho carrinho = this.buscarCarrinhoPorCliente(httpServletRequest);
        Produto produto = this.produtoService.buscarProdutoPorId(produtoCarrinhoRequest.idProduto());
        ProdutoCarrinho produtoCarrinho = this.produtoCarrinhoService.buscarPorProdutoECarrinho(produto,carrinho)
                .orElseThrow(() -> new EntityNotFoundException("O produto " + produto.getNome() + " não está no seu carrinho!"));

        if(produtoCarrinho.getQtde() > produtoCarrinhoRequest.qtde()){
            produtoCarrinho.setQtde(produtoCarrinho.getQtde() - produtoCarrinhoRequest.qtde());
            this.produtoCarrinhoRepository.save(produtoCarrinho);
        } else {
            this.produtoCarrinhoRepository.deleteById(produtoCarrinho.getId());
            carrinho.getListaDeProdutos().remove(produtoCarrinho);
            this.carrinhoRepository.save(carrinho);
        }

        carrinho = this.buscarCarrinhoPorCliente(httpServletRequest);
        carrinho.setTotal(this.calcularTotal(carrinho.getListaDeProdutos()));

        return CarrinhoAdapter.toResponse(this.carrinhoRepository.save(carrinho));
    }

    @Transactional
    public void esvaziarCarrinho(HttpServletRequest httpServletRequest) {
        Carrinho carrinho = this.buscarCarrinhoPorCliente(httpServletRequest);
        this.produtoCarrinhoRepository.deleteAllByCarrinho(carrinho);
        carrinho.setListaDeProdutos(new ArrayList<>());
        carrinho.setTotal(this.calcularTotal(carrinho.getListaDeProdutos()));
        this.carrinhoRepository.save(carrinho);
    }

    private BigDecimal calcularTotal(List<ProdutoCarrinho> listaProdutoCarrinho) {
        BigDecimal total = BigDecimal.ZERO;
        for (ProdutoCarrinho produtoCarrinho : listaProdutoCarrinho) {
            BigDecimal preco = produtoCarrinho.getProduto().getPreco();
            int quantidade = produtoCarrinho.getQtde();
            total = total.add(preco.multiply(BigDecimal.valueOf(quantidade)));
        }
        return total;
    }

    public Carrinho buscarCarrinhoPorCliente(HttpServletRequest httpServletRequest) {
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        return  this.carrinhoRepository.findByCliente(cliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));
    }

    private void verificarEstoqueDisponivel(Produto produto, int qtde) {
        if(produto.getQtdeEstoque() < qtde)
            throw new IllegalArgumentException("Estoque indisponível!");
    }
}
