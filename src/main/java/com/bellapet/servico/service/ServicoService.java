package com.bellapet.servico.service;

import com.bellapet.servico.http.adapter.ServicoAdapter;
import com.bellapet.servico.http.request.ServicoRequest;
import com.bellapet.servico.http.response.ServicoResponse;
import com.bellapet.servico.persistence.entity.Servico;
import com.bellapet.servico.persistence.repository.ServicoRepository;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;

    public List<ServicoResponse> listarServico() {
        return ServicoAdapter.toResponseList(this.servicoRepository.findAllByStatus(Status.ATIVO));
    }

    public List<ServicoResponse> listarServicoInativo() {
        return ServicoAdapter.toResponseList(this.servicoRepository.findAllByStatus(Status.INATIVO));
    }

    public ServicoResponse buscarServico(Long id) {
        return ServicoAdapter.toResponse(this.buscarServicoPorId(id));
    }

    @Transactional
    public void cadastrarServico(ServicoRequest servicoRequest) {
        this.servicoRepository.save(ServicoAdapter.toServico(new Servico(), servicoRequest));
    }

    @Transactional
    public void atualizarServico(Long idServico, ServicoRequest servicoRequest) {
        Servico servico = this.buscarServicoPorId(idServico);
        this.servicoRepository.save(ServicoAdapter.toServico(servico, servicoRequest));
    }

    @Transactional
    public void atualizarStatusServico(Long idServico) {
        Servico servico = this.buscarServicoPorId(idServico);
        servico.setStatus(servico.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);
        this.servicoRepository.save(servico);
    }

    public Servico buscarServicoPorId(Long idServico) {
        return this.servicoRepository.findById(idServico)
                .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado!"));
    }
}
