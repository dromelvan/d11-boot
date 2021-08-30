package org.d11.boot.parser.model;

import lombok.Data;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for a match in a parsed match page.
 */
@Data
public class MatchData extends ParserModel {

    /**
     * Match id. Not provided by the parser.
     */
    private Long matchId;
    /**
     * WhoScored id of the match.
     */
    private Long whoscoredId;
    /**
     * WhoScored id of the home team.
     */
    private Long homeTeamWhoscoredId;
    /**
     * Name of the home team.
     */
    private String homeTeamName;
    /**
     * WhoScored id of the away team.
     */
    private Long awayTeamWhoscoredId;
    /**
     * Name of the away team.
     */
    private String awayTeamName;
    /**
     * Match kickoff time.
     */
    private LocalDateTime datetime;
    /**
     * Elapsed match time.
     */
    private String elapsed;
    /**
     * Match status.
     */
    private Status status;

    /**
     * List of goal data objects for the goals scored in the match.
     */
    private List<GoalData> goals = new ArrayList<>();
    /**
     * List of player match data objects for players participating in the match. This list is empty if the match is pending.
     */
    private List<PlayerMatchData> players = new ArrayList<>();

    /**
     * Is the match pending or not pending.
     *
     * @return True if the match is pending, false if not.
     */
    @Transient
    public boolean isPending() {
        return this.status.equals(Status.PENDING);
    }

    /**
     * Is the match active or not active.
     *
     * @return True if the match is active, false if not.
     */
    @Transient
    public boolean isActive() {
        return this.status.equals(Status.ACTIVE);
    }

    /**
     * Is the match full time or not full time.
     *
     * @return True if the match is full time, false if not.
     */
    @Transient
    public boolean isFullTime() {
        return this.status.equals(Status.FULL_TIME);
    }

    /**
     * Is the match postponed or not postponed.
     *
     * @return True if the match is postponed, false if not.
     */
    @Transient
    public boolean isPostponed() {
        return this.status.equals(Status.POSTPONED);
    }

}
