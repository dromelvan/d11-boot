package org.d11.boot.application.model.converter;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
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
        final StringBuilder stringBuilder = new StringBuilder();
        if(!integers.isEmpty()) {
            for(final Integer integer : integers) {
                stringBuilder.append(integer);
                stringBuilder.append(DELIMITER);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Integer> convertToEntityAttribute(final String string) {
        final List<Integer> integers = new ArrayList<>();
        if(!StringUtils.isEmpty(string)) {
            for(final String integer : string.split(DELIMITER)) {
                integers.add(Integer.parseInt(integer));
            }
        }
        return integers;
    }

}
