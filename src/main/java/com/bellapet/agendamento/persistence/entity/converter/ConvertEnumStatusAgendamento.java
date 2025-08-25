package com.bellapet.agendamento.persistence.entity.converter;

import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConvertEnumStatusAgendamento implements AttributeConverter<StatusAgendamento, String> {
    @Override
    public String convertToDatabaseColumn(StatusAgendamento attribute) {
        return Objects.isNull(attribute) ? null : attribute.getValue();
    }

    @Override
    public StatusAgendamento convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return Stream.of(StatusAgendamento.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
