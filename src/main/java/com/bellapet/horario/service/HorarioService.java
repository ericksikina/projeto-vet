package com.bellapet.horario.service;

import com.bellapet.horario.http.adapter.HorarioAdapter;
import com.bellapet.horario.http.request.HorarioRequest;
import com.bellapet.horario.http.response.HorarioResponse;
import com.bellapet.horario.persistence.entity.Horario;
import com.bellapet.horario.persistence.repository.HorarioRepository;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorarioService {
    private final HorarioRepository horarioRepository;

    public List<HorarioResponse> listarHorario() {
        return HorarioAdapter.toResponseList(this.horarioRepository.findAllByStatus(Status.ATIVO));
    }

    public List<HorarioResponse> listarHorarioInativo() {
        return HorarioAdapter.toResponseList(this.horarioRepository.findAllByStatus(Status.INATIVO));
    }

    public HorarioResponse buscarHorario(Long id) {
        return HorarioAdapter.toResponse(this.buscarHorarioPorId(id));
    }

    @Transactional
    public void cadastrarHorario(HorarioRequest horarioRequest) {
        this.horarioRepository.save(HorarioAdapter.toHorario(new Horario(), horarioRequest));
    }

    @Transactional
    public void atualizarHorario(Long idHorario, HorarioRequest horarioRequest) {
        Horario Horario = this.buscarHorarioPorId(idHorario);
        this.horarioRepository.save(HorarioAdapter.toHorario(Horario, horarioRequest));
    }

    @Transactional
    public void atualizarStatusHorario(Long idHorario) {
        Horario Horario = this.buscarHorarioPorId(idHorario);
        Horario.setStatus(Horario.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);
        this.horarioRepository.save(Horario);
    }

    public Horario buscarHorarioPorId(Long idHorario) {
        return this.horarioRepository.findById(idHorario)
                .orElseThrow(() -> new EntityNotFoundException("Horario não encontrado!"));
    }
}
