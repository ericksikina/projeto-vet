package com.bellapet.agendamento.service;

import com.bellapet.agendamento.http.adapter.AgendamentoAdapter;
import com.bellapet.agendamento.http.request.AgendamentoRequest;
import com.bellapet.agendamento.http.response.AgendamentoResponse;
import com.bellapet.agendamento.persistence.entity.Agendamento;
import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.agendamento.persistence.repository.AgendamentoRepository;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.service.ClienteService;
import com.bellapet.horario.persistence.entity.Horario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;

    public List<AgendamentoResponse> listarAgendamento(HttpServletRequest httpServletRequest) {
        Cliente cliente = this.buscarCliente(httpServletRequest);
        return AgendamentoAdapter.toResponseList(agendamentoRepository.findAllByCliente(cliente));
    }

    public List<AgendamentoResponse> listarAgendamento() {
        return AgendamentoAdapter.toResponseList(agendamentoRepository.findAll());
    }

    public AgendamentoResponse buscarAgendamento(Long id) {
        return AgendamentoAdapter.toResponse(this.buscarAgendamentoPorId(id));
    }

    public AgendamentoResponse buscarAgendamento(HttpServletRequest httpServletRequest, Long id) {
        Cliente cliente = this.buscarCliente(httpServletRequest);
        return AgendamentoAdapter.toResponse(this.buscarAgendamentoPorIdECliente(id,cliente));
    }

    @Transactional
    public void efetuarAgendamento(HttpServletRequest httpServletRequest, AgendamentoRequest agendamentoRequest) {
        Cliente cliente = this.buscarCliente(httpServletRequest);
        this.agendamentoRepository.save(AgendamentoAdapter.toEntity(new Agendamento(), agendamentoRequest, cliente));
    }

    @Transactional
    public void remarcarAgendamento(HttpServletRequest httpServletRequest, AgendamentoRequest agendamentoRequest, Long id) {
        Cliente cliente = this.buscarCliente(httpServletRequest);
        Agendamento agendamento = this.buscarAgendamentoPorIdECliente(id, cliente);
        this.agendamentoRepository.save(AgendamentoAdapter.toEntity(agendamento, agendamentoRequest));
    }

    @Transactional
    public void cancelarAgendamento(HttpServletRequest httpServletRequest, Long id) {
        Cliente cliente = this.buscarCliente(httpServletRequest);
        Agendamento agendamento = this.buscarAgendamentoPorIdECliente(id, cliente);

        this.podeSerCancelado(agendamento);
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        this.agendamentoRepository.save(agendamento);
    }

    private Agendamento buscarAgendamentoPorId(Long id) {
        return this.agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!"));
    }

    private Agendamento buscarAgendamentoPorIdECliente(Long id, Cliente cliente) {
        return this.agendamentoRepository.findByIdAndCliente(id, cliente)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!"));
    }

    private Cliente buscarCliente(HttpServletRequest httpServletRequest) {
        return this.clienteService.buscarPorAuth(httpServletRequest);
    }

    private void podeSerCancelado(Agendamento agendamento) {
        LocalDateTime dataHora = LocalDateTime.of(agendamento.getData(),agendamento.getHora());
        Duration diferenca = Duration.between(LocalDateTime.now(), dataHora);

        if (dataHora.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Não é possível cancelar um agendamento anterior a data atual!");

        if (diferenca.toHours() < 12) {
            throw new IllegalArgumentException("O agendamento deve ser cancelado com pelo menos 12 horas de antecedência!");
        }
    }

    public List<LocalTime> listarHorariosAgendados(LocalDate data) {
        List<Agendamento> listaDeAgendamento = this.agendamentoRepository.findAllByData(data);
        return listaDeAgendamento.stream()
                .map(Agendamento::getHora)
                .collect(Collectors.toList());
    }
}
