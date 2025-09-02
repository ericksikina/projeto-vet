package com.bellapet.produto.service;

import com.bellapet.pedido.persistence.entity.Pedido;
import com.bellapet.produto.http.adapter.ProdutoAdapter;
import com.bellapet.produto.http.request.ProdutoRequest;
import com.bellapet.produto.http.response.ProdutoResponse;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produto.persistence.repository.ProdutoRepository;
import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.tipoProduto.service.TipoProdutoService;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final TipoProdutoService tipoProdutoService;

    public List<ProdutoResponse> listarProduto() {
        return ProdutoAdapter.toResponseList(this.produtoRepository.findAllByStatus(Status.ATIVO));
    }

    public List<ProdutoResponse> listarProdutoInativos() {
        return ProdutoAdapter.toResponseList(this.produtoRepository.findAllByStatus(Status.INATIVO));
    }

    public List<ProdutoResponse> listarProdutoComEstoqueBaixo() {
        return ProdutoAdapter.toResponseList(this.produtoRepository.findProdutosComEstoqueAbaixoDoMinimo());
    }

    public ProdutoResponse buscarProduto(Long id) {
        return ProdutoAdapter.toResponse(this.buscarProdutoPorId(id));
    }

    @Transactional
    public ProdutoResponse cadastrarProduto(ProdutoRequest produtoRequest) throws IOException {
        TipoProduto tipoProduto = this.tipoProdutoService.buscarTipoProdutoPorId(produtoRequest.idTipoProduto());
        Produto produto = ProdutoAdapter.toProduto(new Produto(), produtoRequest, tipoProduto);

        return ProdutoAdapter.toResponse(this.produtoRepository.save(produto));
    }

    @Transactional
    public void atualizarFoto(Long id, MultipartFile file) throws IOException {
        Produto produto = this.buscarProdutoPorId(id);
        produto.setFoto(file.getBytes());
        this.produtoRepository.save(produto);
    }

    @Transactional
    public void atualizarProduto(Long idProduto, ProdutoRequest produtoRequest) {
        Produto Produto = this.buscarProdutoPorId(idProduto);
        TipoProduto tipoProduto = this.tipoProdutoService.buscarTipoProdutoPorId(produtoRequest.idTipoProduto());
        this.produtoRepository.save(ProdutoAdapter.toProduto(Produto, produtoRequest, tipoProduto));
    }

    @Transactional
    public void atualizarStatusProduto(Long idProduto) {
        Produto produto = this.buscarProdutoPorId(idProduto);
        produto.setStatus(produto.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);
        this.produtoRepository.save(produto);
    }

    public void reduzirQtdeEstoque(Produto produto, int qtde) {
        produto.setQtdeEstoque(produto.getQtdeEstoque() - qtde);
        this.produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
    }

    public void atualizarEstoqueAposCancelamentoPedido(Pedido pedido) {
        pedido.getListaDeProdutos()
                .forEach(produtoPedido -> {
                    produtoPedido.getProduto().setQtdeEstoque(produtoPedido.getProduto().getQtdeEstoque() + produtoPedido.getQtde());
                    this.produtoRepository.save(produtoPedido.getProduto());
                });
    }

    public HttpHeaders buscarHttpHeaders(byte[] imagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(this.detectarContentType(imagem)));

        return headers;
    }

    private String detectarContentType(byte[] imagem) {
        String mimeType;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imagem)) {
            mimeType = URLConnection.guessContentTypeFromStream(bais);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
        } catch (IOException e) {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }
}
