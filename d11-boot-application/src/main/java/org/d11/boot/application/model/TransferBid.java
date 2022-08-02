package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.validation.TransferFee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
    @PositiveOrZero
    private int activeFee;

    /**
     * Is the bid successful or not.
     */
    private boolean successful;

    /**
     * The transfer day the bid is for.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferDay transferDay;

    /**
     * The player the bid is for.
     */
    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The D11 team the bid is from.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    @Override
    public void prePersist() {
        super.prePersist();
        this.activeFee = this.fee;
    }

}

