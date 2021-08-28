package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;
import org.d11.boot.parser.model.GoalData;
import org.d11.boot.parser.model.Lineup;
import org.d11.boot.parser.model.PlayerMatchData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents data for a team in match centre data (provided under 'home' and 'away' keys. Example:
 * "home": {
 * "teamId":161,
 * ...
 * "incidentEvents": { .. },
 * ...
 * "name":"Wolves",
 * ...
 * "players":[ .. ],
 * ...
 * "scores":{"halftime":1,"fulltime":1,"running":1},
 * ...
 * }
 */
@Data
public class Team {

    /**
     * WhoScored id of the team.
     */
    private Integer teamId;
    /**
     * Team name.
     */
    private String name;
    /**
     * Scores data.
     */
    private Scores scores;
    /**
     * List of player stat objects.
     */
    private List<Player> players;
    /**
     * List of incident events (goals, cards, etc).
     */
    private List<IncidentEvent> incidentEvents;

    /**
     * Helper for getting values from a set of incident events.
     */
    private IncidentEventHelper incidentEventHelper;

    /**
     * Gets an incident helper for the set of incident events for the team.
     *
     * @return Incident helper for the set of incident events for the team.
     */
    private IncidentEventHelper getIncidentEventHelper() {
        if(this.incidentEventHelper == null) {
            this.incidentEventHelper = new IncidentEventHelper(this.incidentEvents);
        }
        return this.incidentEventHelper;
    }

    /**
     * Gets the number of goals scored by the team in the match.
     *
     * @return The number of goals scored by the team in the match.
     */
    public int getGoalsScored() {
        return getIncidentEventHelper().getIncidentEventCount(Type.TYPE_GOAL);
    }

    /**
     * Creates a list of player match data for this team from the player list and the incident event list.
     *
     * @param goalsConceded We can't find the number of goals conceded from the incident events so it has to be provided.
     * @return List of player match data for this team.
     */
    public List<PlayerMatchData> getPlayerMatchDatas(final int goalsConceded) {
        final List<PlayerMatchData> playerMatchDatas = new ArrayList<>();

        final TreeMap<Integer, Set<PlayerMatchData>> ratingMap = new TreeMap<>();

        for(final Player player : this.players) {
            final PlayerMatchData playerMatchData = new PlayerMatchData();
            playerMatchData.setPlayerWhoscoredId(player.getPlayerId());
            playerMatchData.setPlayerName(player.getName());
            playerMatchData.setCountryId(1L); // Can't parse country from WhoScored match page. 1 = Unknown.
            playerMatchData.setPositionId(player.getPositionId());
            playerMatchData.setTeamWhoscoredId(this.teamId);
            playerMatchData.setTeamName(this.name);
            playerMatchData.setLineup(player.isFirstEleven() ? Lineup.STARTING_LINEUP : Lineup.SUBSTITUTE);
            playerMatchData.setSubstitutionOnTime(getIncidentEventHelper().getSubstitutionOnTime(player.getPlayerId()));
            playerMatchData.setSubstitutionOffTime(getIncidentEventHelper().getSubstitutionOffTime(player.getPlayerId()));
            playerMatchData.setGoals(getIncidentEventHelper().getGoals(player.getPlayerId()));
            playerMatchData.setGoalAssists(getIncidentEventHelper().getGoalAssists(player.getPlayerId()));
            playerMatchData.setOwnGoals(getIncidentEventHelper().getOwnGoals(player.getPlayerId()));
            playerMatchData.setGoalsConceded(goalsConceded);
            playerMatchData.setYellowCardTime(getIncidentEventHelper().getYellowCardTime(player.getPlayerId()));
            playerMatchData.setRedCardTime(getIncidentEventHelper().getRedCardTime(player.getPlayerId()));
            playerMatchData.setManOfTheMatch(false);
            playerMatchData.setSharedManOfTheMatch(false);
            playerMatchData.setRating(player.getRating());
            playerMatchData.setPlayedPosition(player.getPosition());
            playerMatchData.setHeight(player.getHeight());

            playerMatchDatas.add(playerMatchData);

            ratingMap.computeIfAbsent(player.getRating(), rating -> new HashSet<>()).add(playerMatchData);
        }

        final Set<PlayerMatchData> moms = ratingMap.lastEntry().getValue();
        moms.forEach(playerMatchData -> {
            playerMatchData.setManOfTheMatch(moms.size() == 1);
            playerMatchData.setSharedManOfTheMatch(moms.size() == 2);
        });

        return playerMatchDatas;
    }

    /**
     * Creates a list of goals for this team from the incident event list.
     *
     * @param playerIdNameDictionary Incident events don't contain player names so we need to provide them this way.
     * @return List of goals for this team.
     */
    public List<GoalData> getGoalDatas(final PlayerIdNameDictionary playerIdNameDictionary) {
        final List<GoalData> goalDatas = new ArrayList<>();

        for(final IncidentEvent incidentEvent : getIncidentEventHelper().getIncidentEvents(Type.TYPE_GOAL)) {
            final GoalData goalData = new GoalData();
            goalData.setTime(incidentEvent.getMinute());
            goalData.setPenalty(incidentEvent.getQualifierTypes().contains(Type.TYPE_PENALTY));
            goalData.setOwnGoal(incidentEvent.getQualifierTypes().contains(Type.TYPE_OWN_GOAL));
            goalData.setPlayerWhoscoredId(incidentEvent.getPlayerId());
            goalData.setPlayerName(playerIdNameDictionary.getPlayerName(String.valueOf(incidentEvent.getPlayerId())));
            goalData.setTeamWhoscoredId(this.teamId);
            goalData.setTeamName(this.name);
            goalDatas.add(goalData);
        }

        return goalDatas;
    }
}
