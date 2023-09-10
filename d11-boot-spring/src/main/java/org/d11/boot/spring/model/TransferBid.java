package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.validation.TransferFee;

/**
 * A transfer bid in a transfer day.
 */
@Data
@Entity
public class TransferBid extends D11Entity {

    /**
     * Player ranking at the time of the bid.
     */
    @Positive
    private int playerRanking;

    /**
     * D11 team ranking.
     */
    @Column(name = "d11_team_ranking")
    @Positive
    private int d11TeamRanking;

    /**
     * The original fee of the bid.
     */
    @TransferFee
    private int fee;

    /**
     * The active fee of the bid.
     */
    @TransferFee
    private int activeFee;

    /**
     * Transfer bid success status.
     */
    private boolean successful;

    /**
     * The transfer day the bid is for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferDay transferDay;

    /**
     * The player the bid is for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The D11 team the bid is from.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    @Override
    public void prePersist() {
        // When bids are made, active fee should be set to original fee. The active fee will be adjusted later when we
        // handle all the bids.
        super.prePersist();
        this.activeFee = this.fee;
    }

}
