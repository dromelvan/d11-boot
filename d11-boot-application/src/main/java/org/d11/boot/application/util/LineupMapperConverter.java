package org.d11.boot.application.util;

import org.d11.boot.application.model.jpa.Lineup;
import org.modelmapper.AbstractConverter;

/**
 * Converts a lineup enum to an integer.
 */
public class LineupMapperConverter extends AbstractConverter<Lineup, Integer> {

    @Override
    protected Integer convert(final Lineup lineup) {
        return lineup.getId();
    }

}
