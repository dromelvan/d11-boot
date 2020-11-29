package org.d11.boot.application.model;

import lombok.Data;
import org.d11.boot.application.model.converter.CardTypeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * A card in a Premier League match.
 */
@Data
@Entity
public class Card extends MatchEvent {

    /**
     * The type of card that was given.
     */
    @NotNull
    @Convert(converter = CardTypeConverter.class)
    private CardType cardType;

}
