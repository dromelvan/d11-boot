package org.d11.boot.application.model.jpa.converter;

import org.d11.boot.application.model.jpa.Lineup;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts between integer and Lineup.
 */
@Converter
public class LineupConverter implements AttributeConverter<Lineup, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Lineup lineup) {
        return lineup.getId();
    }

    @Override
    public Lineup convertToEntityAttribute(final Integer id) {
        return Lineup.withId(id);
    }

}
