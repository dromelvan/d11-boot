package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;
import org.d11.boot.parser.model.ParsedGoalData;
import org.d11.boot.parser.model.ParsedPlayerMatchData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the top level match centre data object in a WhoScored match page script element. Example:
 * require.config.params["args"] = {
 * matchId:1485563,
 * matchCentreData: {
 * "playerIdNameDictionary": { .. },
 * ...
 * "elapsed: "FT",
 * ...
 * "score: "2 : 0",
 * ...
 * "home": { .. },
 * "away": { .. },
 * ...
 * },
 * matchCentreEventTypeJson: { .. },
 * ...
 * }
 */
@Data
public class MatchCenterData {

    /**
     * Pattern for script element containing match centre data.
     */
    public static final Pattern MATCH_CENTRE_DATA_PATTERN =
            Pattern.compile(".*require.config.params\\[\"args\"] = \\{\\s*" +
                            "matchId:\\d*,\\s*" +
                            "matchCentreData: (\\{.*}),\\s*" +
                            "matchCentreEventTypeJson:.*",
                            Pattern.DOTALL);

    /**
     * Pattern for match score element.
     */
    public static final Pattern SCORE_PATTERN = Pattern.compile("\\*?(\\d*) : (\\d*)\\*?");

    /**
     * Player id <-> name dictionary.
     */
    private PlayerIdNameDictionary playerIdNameDictionary;

    /**
     * Match elapsed time string.
     */
    private String elapsed;

    /**
     * Match score.
     */
    private String score;

    /**
     * Home team data.
     */
    private Team home;

    /**
     * Away team data.
     */
    private Team away;

    /**
     * Gets the 'other' team than the team with the id provided.
     *
     * @param whoscoredId ID of the team we don't want.
     * @return The home team if the away team id is provided and the away team if not.
     */
    public Team getOtherTeam(final long whoscoredId) {
        return whoscoredId == this.home.getTeamId() ? this.away : this.home;
    }

    /**
     * Gets a list of goals for both teams.
     *
     * @return List of goals for both teams.
     */
    public List<ParsedGoalData> getGoals() {
        final List<ParsedGoalData> goals = new ArrayList<>(this.home.getGoalDatas(this.playerIdNameDictionary));
        goals.addAll(this.away.getGoalDatas(this.playerIdNameDictionary));
        // Own goals are part of the other teams incident events for some reason. We have to flip the team.
        goals.forEach(goal -> {
            if (goal.isOwnGoal()) {
                final Team team = getOtherTeam(goal.getTeamSiteId());
                goal.setTeamName(team.getName());
                goal.setTeamSiteId(team.getTeamId());
            }
        });
        return goals;
    }

    /**
     * Gets a list of player match data for both teams.
     *
     * @return List of player match data for both teams.
     */
    public List<ParsedPlayerMatchData> getPlayerMatchDatas() {
        final List<ParsedPlayerMatchData> players = new ArrayList<>(this.home.getPlayerMatchDatas(getAwayTeamGoals()));
        players.addAll(this.away.getPlayerMatchDatas(getHomeTeamGoals()));
        return players;
    }

    /**
     * Elapsed time can sometime have weird values like MT, MS, IY, FIN etc.
     * This method maps those values to non-weird values like HT, FT.
     *
     * @return Sanitized elapsed match time string.
     */
    public String getSanitizedElapsed() {
        // Currently not using this but if it turns out we need to it's here.
        return switch (this.elapsed) {
            case "MT", "IY" -> "HT";
            case "FIN", "MS" -> "FT";
            default -> this.elapsed.replace("+", "");
        };
    }

    private int getHomeTeamGoals() {
        final Matcher matcher = SCORE_PATTERN.matcher(this.score);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1).trim());
        }
        return 0;
    }

    private int getAwayTeamGoals() {
        final Matcher matcher = SCORE_PATTERN.matcher(this.score);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(2).trim());
        }
        return 0;
    }

}
