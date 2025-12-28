package org.d11.boot.spring.model.converter;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Form match points converter tests.
 */
class FormMatchPointsConverterTests {

    /**
     * Test entity attribute.
     */
    private static final List<Integer> ENTITY_ATTRIBUTE = Arrays.asList(1, 2, 3, 4, 5);

    /**
     * Test database column value.
     */
    private static final String DATABASE_COLUMN = "1,2,3,4,5";

    /**
     * Tests FormMatchPointsConverter::convertToDatabaseColumn.
     */
    @Test
    void testConvertToDatabaseColumn() {
        final FormMatchPointsConverter converter = new FormMatchPointsConverter();

        final String result = converter.convertToDatabaseColumn(ENTITY_ATTRIBUTE);

        assertEquals(DATABASE_COLUMN, result);
        assertEquals(StringUtils.EMPTY, converter.convertToDatabaseColumn(null));
        assertEquals(StringUtils.EMPTY, converter.convertToDatabaseColumn(new ArrayList<>()));
    }

    /**
     * Tests FormMatchPointsConverter::convertToEntityAttribute.
     */
    @Test
    void testConvertToEntityAttribute() {
        final FormMatchPointsConverter converter = new FormMatchPointsConverter();

        final List<Integer> result = converter.convertToEntityAttribute(DATABASE_COLUMN);

        assertEquals(ENTITY_ATTRIBUTE, result);
        assertEquals(new ArrayList<>(), converter.convertToEntityAttribute(null));
        assertEquals(new ArrayList<>(), converter.convertToEntityAttribute(StringUtils.EMPTY));
    }

}
