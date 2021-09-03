package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.jpa.converter.FormMatchPointsConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

/**
 * Stats and information for one player and one season.
 */
@Data
@Entity
public class PlayerSeasonStat extends PlayerStatSummary {

    /**
     * Current fee paid for the player by a D11 team.
     */
    @PositiveOrZero
    private int value;

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
     * List of points won the last five individual matches.
     */
    @NotNull
    @Convert(converter = FormMatchPointsConverter.class)
    private List<Integer> formMatchPoints = new ArrayList<>();

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
     * The season the stats are for.
     */
    @ManyToOne
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

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

        for(final PlayerMatchStat playerMatchStat : playerMatchStats) {
            updateStats(playerMatchStat);
            if(playerMatchStat.getRating() > 0) {
                rating += playerMatchStat.getRating();
                ratingMatches++;
            }
        }
        if(ratingMatches > 0) {
            setRating(rating / ratingMatches);
            setPointsPerAppearance(getPoints() / ratingMatches);
        }

        final int toIndex = playerMatchStats.size();
        final int fromIndex = Math.max(0, toIndex - 5);
        final List<PlayerMatchStat> formPlayerMatchStats = playerMatchStats.subList(fromIndex, toIndex);

        for(final PlayerMatchStat playerMatchStat : formPlayerMatchStats) {
            getFormMatchPoints().add(playerMatchStat.getPoints());
            setFormPoints(getFormPoints() + playerMatchStat.getPoints());
        }
    }

}
