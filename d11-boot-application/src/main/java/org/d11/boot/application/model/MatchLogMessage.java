package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.MatchLogMessageTypeConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Log message from match update.
 */
@Data
@Entity
public class MatchLogMessage extends D11Entity {

    /**
     * Log message type.
     */
    @NotNull
    @Convert(converter = MatchLogMessageTypeConverter.class)
    private MatchLogMessageType matchLogMessageType;

    /**
     * Log message.
     */
    @NotEmpty
    private String message;

    /**
     * The match that was updated when the log message was created.
     */
    @ManyToOne
    @JoinColumn(name = "match_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Match match;

}
