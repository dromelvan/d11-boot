package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.converter.StatusConverter;
import org.d11.boot.util.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A transfer day in a transfer window. Contains transfer listings, bids and transfers.
 */
@Data
@Entity
public class TransferDay extends D11Entity implements Comparable<TransferDay> {

    /**
     * Transfer day comparator.
     */
    private static final Comparator<TransferDay> COMPARATOR = Comparator.comparing(TransferDay::getDatetime).reversed();

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transfer_window_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferWindow transferWindow;

    /**
     * List of transfer listings in this transfer day.
     */
    @OneToMany(mappedBy = "transferDay", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferListing> transferListings = new ArrayList<>();

    /**
     * List of transfer bids in this transfer day.
     */
    @OneToMany(mappedBy = "transferDay", cascade = CascadeType.PERSIST)
    @OrderBy("playerRanking, activeFee DESC, d11TeamRanking DESC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferBid> transferBids = new ArrayList<>();

    @Override
    public int compareTo(final TransferDay transferDay) {
        return COMPARATOR.compare(this, transferDay);
    }

}
