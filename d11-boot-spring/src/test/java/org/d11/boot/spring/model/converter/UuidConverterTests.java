package org.d11.boot.spring.model.converter;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * UUID converter tests.
 */
class UuidConverterTests {

    /**
     * Tests UuidConverter::convertToDatabaseColumn.
     */
    @Test
    void testConvertToDatabaseColumn() {
        final UuidConverter converter = new UuidConverter();

        final UUID source = UUID.randomUUID();
        final String result = converter.convertToDatabaseColumn(source);

        assertEquals(source.toString(), result);
        assertNull(converter.convertToDatabaseColumn(null));
    }

    /**
     * Tests UuidConverter::convertToEntityAttribute.
     */
    @Test
    void testConvertToEntityAttribute() {
        final UuidConverter converter = new UuidConverter();

        final String source = UUID.randomUUID().toString();
        final UUID result = converter.convertToEntityAttribute(source);

        assertEquals(UUID.fromString(source), result);
        assertNull(converter.convertToEntityAttribute(null));
    }

}
