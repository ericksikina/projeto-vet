package com.bellapet.cliente.service;

import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.auth.persistence.respository.AuthRepository;
import com.bellapet.auth.service.AuthService;
import com.bellapet.auth.service.TokenService;
import com.bellapet.cliente.http.adapter.ClienteAdapter;
import com.bellapet.cliente.http.request.AtualizarClienteRequest;
import com.bellapet.cliente.http.request.CadastroClienteRequest;
import com.bellapet.cliente.http.request.EmailRecuperacaoRequest;
import com.bellapet.cliente.http.response.ClienteResponse;
import com.bellapet.cliente.http.response.EsqueciMinhaSenhaResponse;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.persistence.repository.ClienteRepository;
import com.bellapet.endereco.http.adapter.EnderecoAdapter;
import com.bellapet.endereco.persistence.entity.Endereco;
import com.bellapet.cliente.http.request.EsqueciMinhaSenhaRequest;
import com.bellapet.utils.enums.Status;
import com.bellapet.utils.enums.UserRole;
import com.bellapet.utils.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final AuthService authService;
    private final TokenService tokenService;
    private final AuthRepository authRepository;
    private final EmailService emailService;

    public List<ClienteResponse> listarCliente() {
        return ClienteAdapter.toResponseList(this.clienteRepository.findALlByStatus(Status.ATIVO));
    }

    public List<ClienteResponse> listarClienteInativo() {
        return ClienteAdapter.toResponseList(this.clienteRepository.findALlByStatus(Status.INATIVO));
    }

    @Transactional
    public void cadastrarCliente(CadastroClienteRequest cadastroClienteRequest) {
        this.cpfJaCadastrado(cadastroClienteRequest.cpf());

        Auth auth = this.authService.cadastro(cadastroClienteRequest.authRequest(), UserRole.USER);
        Endereco endereco = EnderecoAdapter.toEndereco(new Endereco(), cadastroClienteRequest.enderecoRequest());
        this.clienteRepository.save(ClienteAdapter.toCliente(new Cliente(), cadastroClienteRequest, endereco, auth));
    }

    @Transactional
    public void atualizarCliente(HttpServletRequest httpServletRequest, AtualizarClienteRequest atualizarClienteRequest) {
        Cliente cliente = this.buscarPorAuth(httpServletRequest);
        if(!cliente.getCpf().equals(atualizarClienteRequest.cpf()))
            this.cpfJaCadastrado(atualizarClienteRequest.cpf());
        this.clienteRepository.save(ClienteAdapter.toCliente(cliente, atualizarClienteRequest));
    }

    @Transactional
    public void atualizarStatusCliente(HttpServletRequest httpServletRequest) {
        Cliente Cliente = this.buscarPorAuth(httpServletRequest);
        Cliente.setStatus(Cliente.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);
        this.clienteRepository.save(Cliente);
    }

    public EsqueciMinhaSenhaResponse enviarEmailConfirmacao(EmailRecuperacaoRequest emailRecuperacaoRequest) {
        if(this.authRepository.findByLogin(emailRecuperacaoRequest.email()).isEmpty())
            throw new IllegalArgumentException("Nenhum cliente cadastrado com esse email!");

        String codigoRecuperacao = this.gerarCodigoRescuperacao();
        String assunto = "Atualização do seu pedido - Bellapet";
        this.emailService.enviarEmailTexto(emailRecuperacaoRequest.email(), this.gerarMensagemEmail(codigoRecuperacao), assunto);
        return new EsqueciMinhaSenhaResponse(codigoRecuperacao);
    }

    @Transactional
    public void atualizarSenha(EsqueciMinhaSenhaRequest esqueciMinhaSenhaRequest) {
        Auth auth = this.authRepository.findByLogin(esqueciMinhaSenhaRequest.email()).get();
        auth.setPassword(new BCryptPasswordEncoder().encode(esqueciMinhaSenhaRequest.senha()));
        this.authRepository.save(auth);
    }

    public Cliente buscarPorAuth(HttpServletRequest request) {
        String token = this.recuperarToken(request);
        String login = this.tokenService.getLoginFromToken(token);
        Auth auth = this.authRepository.findByLogin(login).get();

        return this.clienteRepository.findByAuth(auth);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }

    private String gerarMensagemEmail(String codigoRecuperacao){
        return "Informe o código a baixo para seguir com a recuperação de senha:" + codigoRecuperacao;
    }

    private String gerarCodigoRescuperacao() {
        return String.valueOf(new Random().nextInt(999999));
    }

    private void cpfJaCadastrado(String cpf){
        Optional<Cliente> optionalCliente = this.clienteRepository.findByCpf(cpf);
        if(optionalCliente.isPresent())
            throw new IllegalArgumentException("Cpf já cadastrado!");
    }
}
