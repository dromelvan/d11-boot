package org.d11.boot.application.model.jpa.converter;

import org.d11.boot.application.model.jpa.CardType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts between integer and CardType.
 */
@Converter
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final CardType cardType) {
        return cardType.getId();
    }

    @Override
    public CardType convertToEntityAttribute(final Integer id) {
        return CardType.withId(id);
    }

}
