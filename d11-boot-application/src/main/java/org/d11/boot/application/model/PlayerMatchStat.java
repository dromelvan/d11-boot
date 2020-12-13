package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.LineupConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Stats for one player in one Premier League match.
 */
@Data
@Entity
public class PlayerMatchStat extends PlayerStat {

    /**
     * Played position from stat source. This is just for posterity and not used for points calculation.
     */
    @NotEmpty
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
    @Max(MatchEvent.MAX_MATCH_EVENT_TIME)
    private int substitutionOnTime;
    /**
     * Minute the player was substituted off. 0 if the player was not substituted off.
     */
    @Min(0)
    @Max(MatchEvent.MAX_MATCH_EVENT_TIME)
    private int substitutionOffTime;
    /**
     * Minute the player was given a yellow card. 0 if the player was not given a yellow card.
     */
    @Min(0)
    @Max(MatchEvent.MAX_MATCH_EVENT_TIME)
    private int yellowCardTime;
    /**
     * Minute the player was given a red card. 0 if the player was not given a red card.
     */
    @Min(0)
    @Max(MatchEvent.MAX_MATCH_EVENT_TIME)
    private int redCardTime;
    /**
     * Man of the match for this match.
     */
    private boolean manOfTheMatch;
    /**
     * Shared man of the match for this match.
     */
    private boolean sharedManOfTheMatch;

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
     * The match the stats are for.
     */
    @ManyToOne
    @JoinColumn(name = "match_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Match match;

    /**
     * The D11match the stats are for.
     */
    @ManyToOne
    @JoinColumn(name = "d11_match_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Match d11Match;

    /**
     * The team the player played for in this match.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The D11 team the player played for in this match.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The position the player played in this match.
     */
    @ManyToOne
    @JoinColumn(name = "position_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Position position;

}
