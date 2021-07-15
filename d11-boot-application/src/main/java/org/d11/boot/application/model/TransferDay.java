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
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A transfer day in a transfer window, containing transfer listings, bids and transfers.
 */
@Data
@Entity
public class TransferDay extends D11Entity implements Comparable<TransferDay> {

    /**
     * The nth transfer day of the transfer window this transfer day is.
     */
    @Positive
    private int transferDayNumber;

    /**
     * The status of the transfer day.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The date and time the window closes and after which transfer bids can no longer be registered.
     */
    @NotNull
    private LocalDateTime datetime;

    /**
     * The transfer window this transfer day belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_window_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferWindow transferWindow;

    @Override
    public int compareTo(final TransferDay transferDay) {
        return Comparator.comparing(TransferDay::getDatetime).reversed().compare(this, transferDay);
    }

}
