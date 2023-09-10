package org.d11.boot.parser.match.whoscored.v1.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import org.d11.boot.parser.model.ParsedGoalData;
import org.d11.boot.parser.model.ParsedPlayerMatchData;
import org.d11.boot.util.Lineup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents data for a team in match centre data (provided under 'home' and 'away' keys). Example:
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
@SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE",
                    justification = "Warning is for auto generated Lombok methods")
public class Team {

    /**
     * WhoScored id of the team.
     */
    private Long teamId;

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
        if (this.incidentEventHelper == null) {
            this.incidentEventHelper = new IncidentEventHelper(this.incidentEvents);
        }
        return this.incidentEventHelper;
    }

    /**
     * Creates a list of player match data for this team from the player list and the incident event list.
     *
     * @param goalsConceded We can't find the number of goals conceded from the incident events, so it has to be
     *                      provided.
     * @return List of player match data for this team.
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public List<ParsedPlayerMatchData> getPlayerMatchDatas(final int goalsConceded) {
        final List<ParsedPlayerMatchData> playerMatchDatas = new ArrayList<>();

        final NavigableMap<Integer, Set<ParsedPlayerMatchData>> ratingMap = new TreeMap<>();

        for (final Player player : this.players) {
            final ParsedPlayerMatchData playerMatchData = new ParsedPlayerMatchData();
            playerMatchData.setSiteId(player.getPlayerId());
            playerMatchData.setName(player.getName());
            playerMatchData.setPositionId(player.getPositionId());
            playerMatchData.setTeamSiteId(this.teamId);
            playerMatchData.setTeamName(this.name);
            playerMatchData.setLineup(player.isFirstEleven() ? Lineup.STARTING_LINEUP : Lineup.SUBSTITUTE);
            playerMatchData.setSubstitutionOnTime(getIncidentEventHelper().getSubstitutionOnTime(player.getPlayerId()));
            playerMatchData
                    .setSubstitutionOffTime(getIncidentEventHelper().getSubstitutionOffTime(player.getPlayerId()));
            playerMatchData.setGoals(getIncidentEventHelper().getGoals(player.getPlayerId()));
            playerMatchData.setGoalAssists(getIncidentEventHelper().getGoalAssists(player.getPlayerId()));
            playerMatchData.setOwnGoals(getIncidentEventHelper().getOwnGoals(player.getPlayerId()));
            playerMatchData.setGoalsConceded(goalsConceded);
            playerMatchData.setYellowCardTime(getIncidentEventHelper().getYellowCardTime(player.getPlayerId()));
            playerMatchData.setRedCardTime(getIncidentEventHelper().getRedCardTime(player.getPlayerId()));
            if (playerMatchData.getRedCardTime() == 0) {
                // If a player gets sent off for two yellow cards, it has a different incident event type.
                // Check that only if we haven't already set red card time from the red card incident event type.
                playerMatchData.setRedCardTime(getIncidentEventHelper().getSecondYellowCardTime(player.getPlayerId()));
            }
            playerMatchData.setManOfTheMatch(false);
            playerMatchData.setSharedManOfTheMatch(false);
            playerMatchData.setRating(player.getRating());
            playerMatchData.setPlayedPosition(player.getPosition());
            playerMatchData.setHeight(player.getHeight());

            playerMatchDatas.add(playerMatchData);

            ratingMap.computeIfAbsent(player.getRating(), rating -> new HashSet<>()).add(playerMatchData);
        }

        final Set<ParsedPlayerMatchData> moms = ratingMap.lastEntry().getValue();
        moms.forEach(playerMatchData -> {
            playerMatchData.setManOfTheMatch(moms.size() == 1);
            playerMatchData.setSharedManOfTheMatch(moms.size() == 2);
        });

        return playerMatchDatas;
    }

    /**
     * Creates a list of goals for this team from the incident event list.
     *
     * @param playerIdNameDictionary Incident events don't contain player names, so we need to provide them this way.
     * @return List of goals for this team.
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public List<ParsedGoalData> getGoalDatas(final PlayerIdNameDictionary playerIdNameDictionary) {
        final List<ParsedGoalData> goalDatas = new ArrayList<>();

        for (final IncidentEvent incidentEvent : getIncidentEventHelper().getIncidentEvents(Type.TYPE_GOAL)) {
            final ParsedGoalData goalData = new ParsedGoalData();
            goalData.setTime(incidentEvent.getMinute());
            goalData.setPenalty(incidentEvent.getQualifierTypes().contains(Type.TYPE_PENALTY));
            goalData.setOwnGoal(incidentEvent.getQualifierTypes().contains(Type.TYPE_OWN_GOAL));
            goalData.setPlayerSiteId(incidentEvent.getPlayerId());
            goalData.setPlayerName(playerIdNameDictionary.getPlayerName(String.valueOf(incidentEvent.getPlayerId())));
            goalData.setTeamSiteId(this.teamId);
            goalData.setTeamName(this.name);
            goalDatas.add(goalData);
        }

        return goalDatas;
    }
}
