package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A transfer window containing a number of transfer days.
 */
@Data
@Entity
public class TransferWindow extends D11Entity implements Comparable<TransferWindow> {

    /**
     * The nth transfer window of the season this transfer window is.
     */
    @PositiveOrZero
    private int transferWindowNumber;

    /**
     * Indicates if the transfer window is a draft window or not.
     */
    private boolean draft;

    /**
     * The status of the transfer window.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The date and time the window closes and after which players can no longer be transfer listed.
     */
    @NotNull
    private LocalDateTime datetime;

    /**
     * The match week the transfers made in this transfer window will be active from.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

    @Override
    public int compareTo(final TransferWindow transferWindow) {
        return Comparator.comparing(TransferWindow::getDatetime).reversed().compare(this, transferWindow);
    }
}
