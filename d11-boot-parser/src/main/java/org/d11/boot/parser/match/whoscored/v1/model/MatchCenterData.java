package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;
import org.d11.boot.parser.model.GoalData;
import org.d11.boot.parser.model.PlayerMatchData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents the top level match centre data object in a WhoScored match page script element. Example:
 * require.config.params["args"] = {
 *     matchId:1485563,
 *     matchCentreData: {
 *          "playerIdNameDictionary": { .. },
 *          ...
 *          "elapsed: "FT",
 *          ...
 *          "home": { .. },
 *          "away": { .. },
 *          ...
 *    },
 *    matchCentreEventTypeJson: { .. },
 *    ...
 * }
 */
@Data
public class MatchCenterData {

    /**
     * Pattern for script element containing match centre data.
     */
    public static final Pattern MATCH_CENTRE_DATA_PATTERN = Pattern.compile(".*require.config.params\\[\"args\"] = \\{\\s*" +
                                                                            "matchId:\\d*,\\s*" +
                                                                            "matchCentreData: (\\{.*}),\\s*" +
                                                                            "matchCentreEventTypeJson:.*",
                                                                            Pattern.DOTALL);

    /**
     * Player id <-> name dictionary.
     */
    private PlayerIdNameDictionary playerIdNameDictionary;
    /**
     * Match elapsed time string.
     */
    private String elapsed;
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
     * @param whoscoredId Id of the team we don't want.
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
    public List<GoalData> getGoals() {
        final List<GoalData> goals = new ArrayList<>(this.home.getGoalDatas(this.playerIdNameDictionary));
        goals.addAll(this.away.getGoalDatas(this.playerIdNameDictionary));
        // Own goals are part of the other teams incident events for some reason. We have to flip the team.
        goals.forEach(goal -> {
            if(goal.isOwnGoal()) {
                final Team team = getOtherTeam(goal.getTeamWhoscoredId());
                goal.setTeamName(team.getName());
                goal.setTeamWhoscoredId(team.getTeamId());
            }
        });
        return goals;
    }

    /**
     * Gets a list of player match data for both teams.
     *
     * @return List of player match data for both teams.
     */
    public List<PlayerMatchData> getPlayerMatchDatas() {
        final List<PlayerMatchData> players = new ArrayList<>(this.home.getPlayerMatchDatas(this.away.getGoalsScored()));
        players.addAll(this.away.getPlayerMatchDatas(this.home.getGoalsScored()));
        return players;
    }

    /**
     * Elapsed time can sometime have weird values like MT, MS, IY, FIN etc.
     * This method maps those values to non weird values like HT, FT.
     *
     * @return Sanitized elapsed match time string.
     */
    public String getSanitizedElapsed() {
        // Currently not using this but if it turns out we need to it's here.
        switch(this.elapsed) {
            case "MT":
            case "IY":
                return "HT";
            case "FIN":
            case "MS":
                return "FT";
            default:
                return this.elapsed.replace("+", "");
        }
    }

}