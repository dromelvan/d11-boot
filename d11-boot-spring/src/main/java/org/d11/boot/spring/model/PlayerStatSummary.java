package org.d11.boot.spring.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.d11.boot.util.Lineup;

/**
 * Base class for classes that provide summaries for player stats.
 */
@Data
@MappedSuperclass
public class PlayerStatSummary extends PlayerStat {

    /**
     * Player ranking.
     */
    @PositiveOrZero
    private int ranking;

    /**
     * Average points per appearance (game started or substituted on).
     */
    private int pointsPerAppearance;

    /**
     * Total number of clean sheets.
     */
    @PositiveOrZero
    private int cleanSheets;

    /**
     * Total number of yellow cards.
     */
    @PositiveOrZero
    private int yellowCards;

    /**
     * Total number red cards.
     */
    @PositiveOrZero
    private int redCards;

    /**
     * Total number of times the player was substituted on.
     */
    @PositiveOrZero
    private int substitutionsOn;

    /**
     * Total number of times the player was substituted off.
     */
    @PositiveOrZero
    private int substitutionsOff;

    /**
     * Total number of times the player was the man of the match.
     */
    @PositiveOrZero
    private int manOfTheMatch;

    /**
     * Total number of times the player was a shared man of the match.
     */
    @PositiveOrZero
    private int sharedManOfTheMatch;

    /**
     * Total number of games started.
     */
    @PositiveOrZero
    private int gamesStarted;

    /**
     * Total number of games started on the bench.
     */
    @PositiveOrZero
    private int gamesSubstitute;

    /**
     * Total number of games the player could have participated in but didn't.
     */
    @PositiveOrZero
    private int gamesDidNotParticipate;

    /**
     * Total number of minutes played.
     */
    @PositiveOrZero
    private int minutesPlayed;

    @Override
    public void reset() {
        super.reset();

        this.ranking = 0;
        this.pointsPerAppearance = 0;
        this.cleanSheets = 0;
        this.yellowCards = 0;
        this.redCards = 0;
        this.substitutionsOn = 0;
        this.substitutionsOff = 0;
        this.manOfTheMatch = 0;
        this.sharedManOfTheMatch = 0;
        this.gamesStarted = 0;
        this.gamesSubstitute = 0;
        this.gamesDidNotParticipate = 0;
        this.minutesPlayed = 0;
    }

    /**
     * Initializes a player stat summary from another player stat summary.
     *
     * @param playerStatSummary The player stat summary with values that will be copied to this one.
     */
    public void init(final PlayerStatSummary playerStatSummary) {
        super.init(playerStatSummary);

        this.ranking = playerStatSummary.getRanking();
        this.pointsPerAppearance = playerStatSummary.getPointsPerAppearance();
        this.cleanSheets = playerStatSummary.getCleanSheets();
        this.yellowCards = playerStatSummary.getYellowCards();
        this.redCards = playerStatSummary.getRedCards();
        this.substitutionsOn = playerStatSummary.getSubstitutionsOn();
        this.substitutionsOff = playerStatSummary.getSubstitutionsOff();
        this.manOfTheMatch = playerStatSummary.getManOfTheMatch();
        this.sharedManOfTheMatch = playerStatSummary.getSharedManOfTheMatch();
        this.gamesStarted = playerStatSummary.getGamesStarted();
        this.gamesSubstitute = playerStatSummary.getGamesSubstitute();
        this.gamesDidNotParticipate = playerStatSummary.getGamesDidNotParticipate();
        this.minutesPlayed = playerStatSummary.getMinutesPlayed();
    }

    @Override
    @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity", "PMD.CognitiveComplexity",
                        "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity" })
    public void addStats(final PlayerStat playerStat) {
        super.addStats(playerStat);

        if (playerStat instanceof PlayerMatchStat playerMatchStat) {
            this.cleanSheets += playerMatchStat.isParticipant() && playerMatchStat.getGoalsConceded() == 0 ? 1 : 0;
            this.yellowCards += playerMatchStat.getYellowCardTime() > 0 ? 1 : 0;
            this.redCards += playerMatchStat.getRedCardTime() > 0 ? 1 : 0;
            this.substitutionsOn += playerMatchStat.getSubstitutionOnTime() > 0 ? 1 : 0;
            this.substitutionsOff += playerMatchStat.getSubstitutionOffTime() > 0 ? 1 : 0;
            this.manOfTheMatch += playerMatchStat.isManOfTheMatch() ? 1 : 0;
            this.sharedManOfTheMatch += playerMatchStat.isSharedManOfTheMatch() ? 1 : 0;
            this.gamesStarted += playerMatchStat.getLineup().equals(Lineup.STARTING_LINEUP) ? 1 : 0;
            this.gamesSubstitute += playerMatchStat.getLineup().equals(Lineup.SUBSTITUTE) ? 1 : 0;
            this.gamesDidNotParticipate += playerMatchStat.getLineup().equals(Lineup.DID_NOT_PARTICIPATE) ? 1 : 0;
            this.minutesPlayed += playerMatchStat.getMinutesPlayed();
        }
    }

}
