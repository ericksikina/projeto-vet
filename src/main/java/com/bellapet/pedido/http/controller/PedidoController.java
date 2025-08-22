package com.bellapet.pedido.http.controller;

import com.bellapet.pagamento.http.request.PagamentoRequest;
import com.bellapet.pedido.http.request.StatusPedidoRequest;
import com.bellapet.pedido.http.response.ResumoPedidoResponse;
import com.bellapet.pedido.http.response.PedidoResponse;
import com.bellapet.pedido.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Pedido")
@RequestMapping(path = "/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<ResumoPedidoResponse>> listarPedidos(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.pedidoService.listarPedidos(httpServletRequest));
    }

    @GetMapping(path = "/buscar/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(this.pedidoService.buscarPedido(id));
    }

    @PostMapping(path = "/efetuar-pedido")
    public ResponseEntity<Void> efetuarPedido(HttpServletRequest httpServletRequest,
                                              @RequestBody PagamentoRequest pagamentoRequest) {
        this.pedidoService.efetuarPedido(httpServletRequest, pagamentoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(path = "/cancelar-pedido/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        this.pedidoService.cancelarPedido(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/atualizar-status-pedido/{id}")
    public ResponseEntity<Void> alterarStatusDaPedido(@PathVariable Long id,
                                                     @RequestBody StatusPedidoRequest statusPedidoRequest) {
        this.pedidoService.alterarStatusDoPedido(id, statusPedidoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
