package org.d11.boot.application.model.jpa.converter;

import org.d11.boot.application.model.jpa.MatchLogMessageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts between integer and MatchLogMessageType.
 */
@Converter
public class MatchLogMessageTypeConverter implements AttributeConverter<MatchLogMessageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final MatchLogMessageType matchLogMessageType) {
        return matchLogMessageType.ordinal();
    }

    @Override
    public MatchLogMessageType convertToEntityAttribute(final Integer ordinal) {
        return MatchLogMessageType.values()[ordinal];
    }

}
