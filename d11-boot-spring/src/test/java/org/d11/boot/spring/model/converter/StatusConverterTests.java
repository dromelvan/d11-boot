package org.d11.boot.spring.model.converter;

import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Status converter tests.
 */
class StatusConverterTests {

    /**
     * Tests StatusConverter::convertToDatabaseColumn.
     */
    @Test
    void testConvertToDatabaseColumn() {
        final StatusConverter converter = new StatusConverter();

        for (final Status status : Status.values()) {
            final Integer result = converter.convertToDatabaseColumn(status);
            assertEquals(status.getId(), result, "StatusConverter::convertToDatabaseColumn equals");
        }
    }

    /**
     * Tests StatusConverter::convertToEntityAttribute.
     */
    @Test
    void testConvertToEntityAttribute() {
        final StatusConverter converter = new StatusConverter();

        for (final Status status : Status.values()) {
            final Status result = converter.convertToEntityAttribute(status.getId());
            assertEquals(status, result, "StatusConverter::convertToEntityAttribute equals");
        }
    }

}
