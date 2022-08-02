package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * A transfer in a transfer day.
 */
@Data
@Entity
public class Transfer extends D11Entity {

    /**
     * Number the fee has to be evenly dividable with.
     */
    public static final int FEE_DIVISOR = 5;

    /**
     * The fee of the transfer.
     */
    @Positive
    private int fee;

    /**
     * The transfer day the transfer is for.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferDay transferDay;

    /**
     * The player the transfer is for.
     */
    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The D11 team the transfer is from.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

}

