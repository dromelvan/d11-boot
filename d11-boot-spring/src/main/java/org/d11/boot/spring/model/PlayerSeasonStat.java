package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.d11.boot.spring.model.converter.FormMatchPointsConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats and information for one player and one season.
 */
@Data
@Entity
@NoArgsConstructor
public class PlayerSeasonStat extends PlayerStatSummary {

    /**
     * Player shirt number.
     */
    @Positive
    private Integer shirtNumber;

    /**
     * Current fee paid for the player by a D11 team.
     */
    @PositiveOrZero
    private int fee;

    /**
     * The nth most valuable player win for this player, if he won this season.
     */
    @Positive
    private Integer winCount;

    /**
     * Total points the last five games.
     */
    private int formPoints;

    /**
     * List of points from the last five individual matches.
     */
    @NotNull
    @Convert(converter = FormMatchPointsConverter.class)
    private List<Integer> formMatchPoints = new ArrayList<>();

    /**
     * The player the stats are for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The season the stats are for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

    /**
     * The team the player belongs to at the current point of this season.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The D11 team the player belongs to at the current point of this season.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The position the player played during this season.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Position position;

    /**
     * Creates a new player season stat.
     *
     * @param player   The player season stat player.
     * @param season   The player season stat season.
     * @param team     The team the player belongs to.
     * @param d11Team  The D11 team the player belongs to.
     * @param position The position the player plays.
     */
    public PlayerSeasonStat(final Player player,
                            final Season season,
                            final Team team,
                            final D11Team d11Team,
                            final Position position) {
        this.player = player;
        this.season = season;
        this.team = team;
        this.d11Team = d11Team;
        this.position = position;
    }


    @Override
    public void reset() {
        super.reset();

        this.formPoints = 0;
        this.formMatchPoints.clear();
    }

    /**
     * Sums up the stats from the provided player match stats.
     *
     * @param playerMatchStats The player match stats that will be summed up.
     */
    public void updateStats(final List<PlayerMatchStat> playerMatchStats) {
        reset();

        int rating = 0;
        int ratingMatches = 0;

        for (final PlayerMatchStat playerMatchStat : playerMatchStats) {
            addStats(playerMatchStat);
            if (playerMatchStat.getRating() > 0) {
                rating += playerMatchStat.getRating();
                ratingMatches++;
            }
        }
        if (ratingMatches > 0) {
            setRating(rating / ratingMatches);
            setPointsPerAppearance(getPoints() / ratingMatches);
        }

        final int toIndex = playerMatchStats.size();
        final int fromIndex = Math.max(0, toIndex - 5);
        final List<PlayerMatchStat> formPlayerMatchStats = playerMatchStats.subList(fromIndex, toIndex);

        for (final PlayerMatchStat playerMatchStat : formPlayerMatchStats) {
            getFormMatchPoints().add(playerMatchStat.getPoints());
            setFormPoints(getFormPoints() + playerMatchStat.getPoints());
        }
    }

}
