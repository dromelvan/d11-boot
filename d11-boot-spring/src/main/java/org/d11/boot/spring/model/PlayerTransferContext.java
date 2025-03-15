package org.d11.boot.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.d11.boot.util.Status;
import org.hibernate.annotations.Immutable;

/**
 * Current transfer context for a player in relation to a user and their D11 team. This projection contains all the
 * information required to determine what transfer actions the current user can currently do with the player.
 */
@Entity
@Getter
@ToString
@Immutable
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@SuppressWarnings("PMD.TooManyFields")
public class PlayerTransferContext {

    /**
     * Max player count.
     */
    public static final int MAX_PLAYER_COUNT = 11;

    /**
     * Max ranking.
     */
    public static final int MAX_RANKING = 20;

    /**
     * Composite ID of playerId and d11TeamId.
     */
    @EmbeddedId
    private final PlayerTransferContextId id;

    /**
     * D11 team owner id that should match the current user.
     */
    @Column(name = "owner_id")
    private final Long ownerId;

    /**
     * D11 team co owner id that should match the current user if the owner id does not match.
     */
    @Column(name = "co_owner_id")
    private final Long coOwnerId;

    /**
     * Current D11 team ranking.
     */
    @Column(name = "ranking")
    private final int ranking;

    /**
     * Current number of players in the D11 team.
     */
    @Column(name = "player_count")
    private final int playerCount;

    /**
     * Current total value of all players in the D11 team.
     */
    @Column(name = "fee_sum")
    private final int feeSum;

    /**
     * The number of transfers the D11 team has already made the current season.
     */
    @Column(name = "transfer_count")
    private final int transferCount;

    /**
     * The number of players in the D11 team with the same position as the context player.
     */
    @Column(name = "position_count")
    private final int positionCount;

    /**
     * The player the context refers to.
     */
    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Player player;

    /**
     * The position the context player has.
     */
    @ManyToOne
    @JoinColumn(name = "position_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Position position;

    /**
     * The D11 team the context player belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "player_d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final D11Team playerD11Team;

    /**
     * The current season.
     */
    @ManyToOne
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Season season;

    /**
     * The current transfer day.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final TransferDay transferDay;

    /**
     * The transfer listing of the context player by the current user D11 team, if any.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_listing_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final TransferListing transferListing;

    /**
     * The transfer bid of the context player by the current user D11 team, if any.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_bid_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final TransferBid transferBid;

    /**
     * The D11 team of the current user.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id", insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final D11Team d11Team;

    /**
     * Gets transfer listable status.
     *
     * @return True if the player can be transfer listed by the D11 team, false if not.
     */
    public boolean isTransferListable() {
        return Status.PENDING.equals(this.transferDay.getStatus())
               && this.transferCount < this.season.getD11TeamMaxTransfers()
               && this.playerD11Team.equals(this.d11Team)
               && this.transferListing == null;
    }

    /**
     * Returns a deletable transfer listing.
     *
     * @return The transfer listing if it exists and can be deleted by the D11 team, null if not.
     */
    public TransferListing getDeletableTransferListing() {
        return Status.PENDING.equals(this.transferDay.getStatus())
               && this.transferListing != null
               && this.d11Team.equals(this.transferListing.getD11Team())
               ? this.transferListing
               : null;
    }

    /**
     * Gets the max bid the D11 team can make on the player.
     *
     * @return The max bid the D11 team can make on the player.
     */
    @SuppressWarnings("checkstyle:BooleanExpressionComplexity")
    public int getMaxBid() {
        final int reservedAmount = playerCount <= MAX_PLAYER_COUNT - 2
                                   ? (MAX_PLAYER_COUNT - 1 - playerCount) * Transfer.FEE_DIVISOR
                                   : 0;

        return Status.ACTIVE.equals(this.transferDay.getStatus())
               && this.playerCount < MAX_PLAYER_COUNT
               && this.positionCount < this.position.getMaxCount()
               && this.transferListing != null
               && !this.d11Team.equals(this.transferListing.getD11Team())
               ? this.season.getD11TeamBudget() - reservedAmount - this.feeSum
               : 0;
    }

    /**
     * Returns a deletable or updatable transfer bid.
     *
     * @return The transfer bid if it exists and can be deleted or updated by the D11 team, null if not.
     */
    public TransferBid getActiveTransferBid() {
        return Status.ACTIVE.equals(this.transferDay.getStatus())
               && this.transferBid != null
               && this.d11Team.equals(this.transferBid.getD11Team())
               ? this.transferBid
               : null;
    }

}
