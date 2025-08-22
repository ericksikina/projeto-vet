package com.bellapet.pedido.persistence.entity.converter;

import com.bellapet.pedido.persistence.entity.enums.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConverterEnumStatusPedido implements AttributeConverter<StatusPedido, String> {
    @Override
    public String convertToDatabaseColumn(StatusPedido attribute) {
        return Objects.isNull(attribute) ? null : attribute.getValue();
    }

    @Override
    public StatusPedido convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return Stream.of(StatusPedido.values())
                .filter(c -> c.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
