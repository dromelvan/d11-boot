package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.converter.LineupConverter;
import org.d11.boot.util.Lineup;

/**
 * Stats for one player in one Premier League match.
 */
@Data
@Entity
public class PlayerMatchStat extends PlayerStat {

    /**
     * Minutes played if the player played the whole match.
     */
    public static final int MAX_MATCH_TIME = 90;

    /**
     * Max length for played position property.
     */
    public static final int MAX_PLAYED_POSITION_LENGTH = 3;

    /**
     * Played position from stat source. This is just for posterity and not used for points calculation.
     */
    @NotEmpty
    @Size(min = 1, max = MAX_PLAYED_POSITION_LENGTH)
    private String playedPosition;

    /**
     * Lineup status for the player in this match.
     */
    @NotNull
    @Convert(converter = LineupConverter.class)
    private Lineup lineup;

    /**
     * Minute the player was substituted on. 0 if the player was not substituted on.
     */
    @Min(0)
    @Max(MAX_MATCH_TIME)
    private int substitutionOnTime;

    /**
     * Minute the player was substituted off. 0 if the player was not substituted off.
     */
    @Min(0)
    @Max(MAX_MATCH_TIME)
    private int substitutionOffTime;

    /**
     * Minute the player was given a yellow card. 0 if the player was not given a yellow card.
     */
    @Min(0)
    @Max(MAX_MATCH_TIME)
    private int yellowCardTime;

    /**
     * Minute the player was given a red card. 0 if the player was not given a red card.
     */
    @Min(0)
    @Max(MAX_MATCH_TIME)
    private int redCardTime;

    /**
     * Player man of the match status for this match.
     */
    private boolean manOfTheMatch;

    /**
     * Player shared man of the match status for this match.
     */
    private boolean sharedManOfTheMatch;

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
     * The match the stats are for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Match match;

    /**
     * The D11 match the stats are for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_match_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Match d11Match;

    /**
     * The team the player played for in this match.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The D11 team the player played for in this match.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The position the player played in this match.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Position position;

    /**
     * Player participant status.
     *
     * @return True if lineup is starting lineup or substitution on time is greater than 0. False otherwise.
     */
    public boolean isParticipant() {
        return getLineup().equals(Lineup.STARTING_LINEUP) || getSubstitutionOnTime() > 0;
    }

    /**
     * Gets minutes played by the player in the match.
     *
     * @return Number of minutes played.
     */
    public int getMinutesPlayed() {
        if (isParticipant()) {
            final int stopTime = this.substitutionOffTime > 0 ? this.substitutionOffTime : MAX_MATCH_TIME;
            return stopTime - this.substitutionOnTime;
        }
        return 0;
    }

    /**
     * Resets all stats.
     */
    @Override
    public void reset() {
        super.reset();

        this.playedPosition = "NA";
        this.lineup = Lineup.DID_NOT_PARTICIPATE;
        this.substitutionOnTime = 0;
        this.substitutionOffTime = 0;
        this.yellowCardTime = 0;
        this.redCardTime = 0;
        this.manOfTheMatch = false;
        this.sharedManOfTheMatch = false;

        if (this.position.isDefender()) {
            setPoints(-1);
        }
    }

}
