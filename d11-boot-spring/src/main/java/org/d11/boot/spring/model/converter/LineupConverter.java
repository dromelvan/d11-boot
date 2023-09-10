package org.d11.boot.spring.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.d11.boot.util.Lineup;


/**
 * Converts a lineup to and from an integer by lineup id.
 */
@Converter
public class LineupConverter implements AttributeConverter<Lineup, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final Lineup lineup) {
        return lineup.getId();
    }

    @Override
    public Lineup convertToEntityAttribute(final Integer id) {
        return Lineup.valueOfId(id);
    }

}
