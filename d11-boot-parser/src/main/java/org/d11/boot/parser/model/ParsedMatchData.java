package org.d11.boot.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.util.Status;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for a match in a parsed match page.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParsedMatchData extends ParsedData {

    /**
     * D11 match id. This is not set by the parser, but it might be a useful property to set when handling parsed match
     * data.
     */
    private Long id;

    /**
     * Match id on whatever site the match data was parsed from.
     */
    private Long siteId;

    /**
     * D11 home team id. This is not set by the parser, but it might be a useful property to set when handling parsed
     * match data.
     */
    private Long homeTeamId;

    /**
     * Home team id on whatever site the match data was parsed from.
     */
    private Long homeTeamSiteId;

    /**
     * Name of the home team.
     */
    private String homeTeamName;

    /**
     * D11 away team id. This is not set by the parser, but it might be a useful property to set when handling parsed
     * match data.
     */
    private Long awayTeamId;

    /**
     * Away team id on whatever site the match data was parsed from.
     */
    private Long awayTeamSiteId;

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
    private List<ParsedGoalData> goals = new ArrayList<>();

    /**
     * List of player match data objects for players participating in the match. This list is empty if the match is
     * pending.
     */
    private List<ParsedPlayerMatchData> players = new ArrayList<>();

    /**
     * Pending status.
     *
     * @return True if the match is pending, false if not.
     */
    @Transient
    public boolean isPending() {
        return this.status.equals(Status.PENDING);
    }

    /**
     * Active status.
     *
     * @return True if the match is active, false if not.
     */
    @Transient
    public boolean isActive() {
        return this.status.equals(Status.ACTIVE);
    }

    /**
     * Full time status.
     *
     * @return True if the match is full time, false if not.
     */
    @Transient
    public boolean isFullTime() {
        return this.status.equals(Status.FULL_TIME);
    }

    /**
     * Postponed status.
     *
     * @return True if the match is postponed, false if not.
     */
    @Transient
    public boolean isPostponed() {
        return this.status.equals(Status.POSTPONED);
    }

}
