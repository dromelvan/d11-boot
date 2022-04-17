package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.FormMatchPointsConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A transfer listing in a transfer day.
 */
@Data
@Entity
public class TransferListing extends PlayerStatSummary {

    /**
     * Total points the last five games.
     */
    private int formPoints;
    /**
     * List of points won the last five individual matches.
     */
    @NotNull
    @Convert(converter = FormMatchPointsConverter.class)
    private List<Integer> formMatchPoints;

    /**
     * A new player has not been available for transfer so far this season.
     */
    private boolean newPlayer;

    /**
     * The transfer day the stats are for.
     */
    @ManyToOne
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferDay transferDay;

    /**
     * The player the stats are for.
     */
    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The team the player belongs to at the current point of this season.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The D11 team the player belongs to at the current point of this season.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The position the player played during this season.
     */
    @ManyToOne
    @JoinColumn(name = "position_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Position position;

    /**
     * Initializes a transfer listing from a player season stat.
     *
     * @param playerSeasonStat The player season stat with values that will be copied to this one.
     */
    public void init(final PlayerSeasonStat playerSeasonStat) {
        super.init(playerSeasonStat);

        this.formPoints = playerSeasonStat.getFormPoints();
        this.formMatchPoints = playerSeasonStat.getFormMatchPoints();

        this.player = playerSeasonStat.getPlayer();
        this.team = playerSeasonStat.getTeam();
        this.d11Team = playerSeasonStat.getD11Team();
        this.position = playerSeasonStat.getPosition();
    }

}

