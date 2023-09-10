package org.d11.boot.spring.model.converter;

import org.d11.boot.util.Lineup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Lineup converter tests.
 */
class LineupConverterTests {

    /**
     * Tests LineupConverter::convertToDatabaseColumn.
     */
    @Test
    void testConvertToDatabaseColumn() {
        final LineupConverter converter = new LineupConverter();

        for (final Lineup lineup : Lineup.values()) {
            final Integer result = converter.convertToDatabaseColumn(lineup);
            assertEquals(lineup.getId(), result, "LineupConverter::convertToDatabaseColumn equals");
        }
    }

    /**
     * Tests LineupConverter::convertToEntityAttribute.
     */
    @Test
    void testConvertToEntityAttribute() {
        final LineupConverter converter = new LineupConverter();

        for (final Lineup lineup : Lineup.values()) {
            final Lineup result = converter.convertToEntityAttribute(lineup.getId());
            assertEquals(lineup, result, "LineupConverter::convertToEntityAttribute equals");
        }
    }

}
