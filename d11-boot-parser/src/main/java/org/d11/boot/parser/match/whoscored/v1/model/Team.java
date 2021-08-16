package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;
import org.d11.boot.api.model.GoalBaseDTO;
import org.d11.boot.api.model.PlayerBaseDTO;
import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.api.model.PlayerNameDTO;
import org.d11.boot.api.model.TeamBaseDTO;
import org.d11.boot.api.model.TeamNameDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents data for a team in match centre data (provided under 'home' and 'away' keys. Example:
 * "home": {
 *     "teamId":161,
 *     ...
 *     "incidentEvents": { .. },
 *     ...
 *     "name":"Wolves",
 *     ...
 *     "players":[ .. ],
 *     ...
 *     "scores":{"halftime":1,"fulltime":1,"running":1},
 *     ...
 * }
 */
@Data
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
     * Creates a list of player match stats for this team from the player list and the incident event list.
     *
     * @param goalsConceded We can't find the number of goals conceded from the incident events so it has to be provided.
     * @return List of player match stats for this team.
     */
    public List<PlayerMatchStatDTO> getPlayerMatchStats(final int goalsConceded) {
        final List<PlayerMatchStatDTO> playerMatchStats = new ArrayList<>();

        final IncidentEventHelper incidentEventHelper = new IncidentEventHelper(this.incidentEvents);
        final TreeMap<Integer, Set<PlayerMatchStatDTO>> ratingMap = new TreeMap<>();

        for(final Player player : this.players) {
            final PlayerMatchStatDTO playerMatchStat = new PlayerMatchStatDTO()
                    .player(new PlayerBaseDTO()
                            .whoscoredId(player.getPlayerId())
                            .name(player.getName()))
                    .team(new TeamBaseDTO()
                            .whoscoredId(this.teamId)
                            .name(this.name))
                    .lineup(player.isFirstEleven() ? 2 : 1)
                    .substitutionOnTime(incidentEventHelper.getSubstitutionOnTime(player.getPlayerId()))
                    .substitutionOffTime(incidentEventHelper.getSubstitutionOffTime(player.getPlayerId()))
                    .goals(incidentEventHelper.getGoals(player.getPlayerId()))
                    .goalAssists(incidentEventHelper.getGoalAssists(player.getPlayerId()))
                    .ownGoals(incidentEventHelper.getOwnGoals(player.getPlayerId()))
                    .goalsConceded(goalsConceded)
                    .yellowCardTime(incidentEventHelper.getYellowCardTime(player.getPlayerId()))
                    .redCardTime(incidentEventHelper.getRedCardTime(player.getPlayerId()))
                    .manOfTheMatch(0)
                    .sharedManOfTheMatch(0)
                    .rating(player.getRating())
                    .playedPosition(player.getPosition());
            playerMatchStats.add(playerMatchStat);

            ratingMap.computeIfAbsent(playerMatchStat.getRating(), rating -> new HashSet<>()).add(playerMatchStat);
        }
        ratingMap.lastEntry().getValue().forEach(playerMatchStatDTO -> playerMatchStatDTO.setManOfTheMatch(1));

        return playerMatchStats;
    }

    /**
     * Creates a list of goals for this team from the incident event list.
     *
     * @param playerIdNameDictionary Incident events don't contain player names so we need to provide them this way.
     * @return List of goals for this team.
     */
    public List<GoalBaseDTO> getGoals(final PlayerIdNameDictionary playerIdNameDictionary) {
        final List<GoalBaseDTO> goals = new ArrayList<>();
        final IncidentEventHelper incidentEventHelper = new IncidentEventHelper(this.incidentEvents);

        for(final IncidentEvent incidentEvent : incidentEventHelper.getIncidentEvents(Type.TYPE_GOAL)) {
            goals.add(new GoalBaseDTO()
                    .team(new TeamNameDTO()
                            .whoscoredId(this.teamId)
                            .name(this.name))
                    .penalty(incidentEvent.getQualifierTypes().contains(Type.TYPE_PENALTY))
                    .ownGoal(incidentEvent.getQualifierTypes().contains(Type.TYPE_OWN_GOAL))
                    .player(new PlayerNameDTO()
                            .whoscoredId(incidentEvent.getPlayerId())
                            .name(playerIdNameDictionary.getPlayerName(String.valueOf(incidentEvent.getPlayerId()))))
                    .time(incidentEvent.getMinute())
            );
        }

        return goals;
    }
}
