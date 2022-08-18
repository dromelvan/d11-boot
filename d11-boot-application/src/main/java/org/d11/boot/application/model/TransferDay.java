package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    /**
     * List of transfer listings in this transfer day.
     */
    @OneToMany(mappedBy = "transferDay", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferListing> transferListings = new ArrayList<>();

    /**
     * List of transfer bids in this transfer day.
     */
    @OneToMany(mappedBy = "transferDay", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferBid> transferBids = new ArrayList<>();

    @Override
    public int compareTo(final TransferDay transferDay) {
        return Comparator.comparing(TransferDay::getDatetime).reversed().compare(this, transferDay);
    }

    /**
     * Gets a transfer listing for a specific player, if such a transfer listing exists.
     *
     * @param playerId Id of the player for which a transfer listing will be looked up.
     * @return Optional of the transfer listing if it exists, empty optional if not.
     */
    public Optional<TransferListing> getTransferListingByPlayerId(final long playerId) {
        return this.transferListings.stream()
                .filter(transferListing -> transferListing.getPlayer().getId() == playerId)
                .findFirst();
    }

    /**
     * Checks if a player is transfer listed this transfer day.
     *
     * @param player The player that will be checked.
     * @return True if the player is transfer listed this transfer day, false if not.
     */
    public boolean isTransferListed(final Player player) {
        return getTransferListingByPlayerId(player.getId()).isPresent();
    }

    /**
     * Checks if this transfer day contains a transfer bid for a player by a D11 team.
     *
     * @param player The player for which a transfer bid will be looked up.
     * @param d11Team The D11 team for which a transfer bid will be looked up
     * @return True if the transfer day contains a transfer bid for the player by the D11 team, false if not.
     */
    public boolean hasTransferBid(final Player player, final D11Team d11Team) {
        return this.transferBids.stream()
                .anyMatch(transferBid -> transferBid.getPlayer().equals(player)
                                         && transferBid.getD11Team().equals(d11Team));
    }

    /**
     * Gets a transfer bid for a player by a D11 team in this transfer day.
     *
     * @param player The player for which a transfer bid will be looked up.
     * @param d11Team The D11 team for which a transfer bid will be looked up
     * @return Transfer bid for the player by the D11 team.
     */
    public Optional<TransferBid> getTransferBid(final Player player, final D11Team d11Team) {
        return this.transferBids.stream()
                .filter(transferBid -> transferBid.getPlayer().equals(player)
                                       && transferBid.getD11Team().equals(d11Team))
                .findFirst();
    }

}
