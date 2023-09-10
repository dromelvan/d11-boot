package org.d11.boot.spring.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Converts a UUID to and from a string.
 */
@Converter
public class UuidConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(final UUID uuid) {
        if (uuid != null) {
            return uuid.toString();
        }
        return null;
    }

    @Override
    public UUID convertToEntityAttribute(final String uuid) {
        if (!StringUtils.isBlank(uuid)) {
            return UUID.fromString(uuid);
        }
        return null;
    }

}
