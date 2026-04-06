package org.d11.boot.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

/**
 * Transfer window context. Keeps track of the number of transfer listed and transferred players for each position
 * during a transfer window.
 */
@Entity
@Getter
@ToString
@Immutable
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TransferWindowPositionCount implements Comparable<TransferWindowPositionCount> {

    /**
     * Composite ID of transferWindowId and positionId.
     */
    @EmbeddedId
    private final TransferWindowPositionCountId id;

    /**
     * Position transfer listing count.
     */
    @Column(name = "transfer_listing_count")
    private final int transferListingCount;

    /**
     * Position transfer count.
     */
    @Column(name = "transfer_count")
    private final int transferCount;

    /**
     * The transfer window the count is for.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_window_id", insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final TransferWindow transferWindow;

    /**
     * The position the count is for.
     */
    @ManyToOne
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Position position;

    @Override
    public int compareTo(final TransferWindowPositionCount transferWindowPositionCount) {
        return this.getPosition().compareTo(transferWindowPositionCount.getPosition());
    }
}
