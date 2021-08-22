package org.d11.boot.application.model.jpa.converter;

import org.d11.boot.application.model.jpa.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
        return Status.withId(id);
    }

}
