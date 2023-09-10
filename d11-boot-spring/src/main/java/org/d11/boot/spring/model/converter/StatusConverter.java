package org.d11.boot.spring.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.d11.boot.util.Status;


/**
 * Converts a status to and from an integer by status id.
 */
@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Status status) {
        return status.getId();
    }

    @Override
    public Status convertToEntityAttribute(final Integer id) {
        return Status.valueOfId(id);
    }

}
