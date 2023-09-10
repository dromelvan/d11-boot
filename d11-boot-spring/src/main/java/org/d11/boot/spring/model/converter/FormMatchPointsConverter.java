package org.d11.boot.spring.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between a String and List of Integer.
 */
@Converter
public class FormMatchPointsConverter implements AttributeConverter<List<Integer>, String> {

    /**
     * Integer string delimiter.
     */
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(final List<Integer> integers) {
        return integers == null || integers.isEmpty()
               ? ""
               : String.join(DELIMITER, integers.stream().map(Object::toString).toList());
    }

    @Override
    public List<Integer> convertToEntityAttribute(final String string) {
        final List<Integer> integers = new ArrayList<>();
        if (!StringUtils.isEmpty(string)) {
            for (final String integer : string.split(DELIMITER)) {
                integers.add(Integer.parseInt(integer));
            }
        }
        return integers;
    }

}
