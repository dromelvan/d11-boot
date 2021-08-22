package org.d11.boot.parser.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for a match in a parsed match page.
 */
@Data
public class MatchData extends ParserModel {

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

}
