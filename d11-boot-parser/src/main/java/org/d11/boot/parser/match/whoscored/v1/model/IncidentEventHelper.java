package org.d11.boot.parser.match.whoscored.v1.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides helper methods for incident events.
 */
public class IncidentEventHelper {

    /**
     * Maps type values to lists of incident events.
     */
    private final Map<Integer, List<IncidentEvent>> typeMap = new HashMap<>();
    /**
     * Maps player ids to maps of type values to lists of incident events.
     */
    private final Map<Long, Map<Integer, List<IncidentEvent>>> playerMap = new HashMap<>();

    /**
     * Creates a new incident event helper.
     *
     * @param incidentEvents Lists of incident events the helper will work with.
     */
    public IncidentEventHelper(final List<IncidentEvent> incidentEvents) {
        for(final IncidentEvent incidentEvent : incidentEvents) {
            getIncidentEvents(incidentEvent.getType().getValue()).add(incidentEvent);
            getIncidentEvents(incidentEvent.getType().getValue(), incidentEvent.getPlayerId()).add(incidentEvent);
        }
    }

    /**
     * Gets a list of incident events of a type.
     *
     * @param type The type value incident events will be looked up for.
     * @return List of incident events of the type.
     */
    public List<IncidentEvent> getIncidentEvents(final int type) {
        final List<IncidentEvent> incidentEvents = this.typeMap.computeIfAbsent(type, key -> new ArrayList<>());
        incidentEvents.sort(Comparator.comparing(IncidentEvent::getMinute));
        return incidentEvents;
    }

    /**
     * Gets incident events of a type for a player.
     *
     * @param type     The type value of events that will be looked up.
     * @param playerId The player events will be looked up for.
     * @return Incident events of a type for a player.
     */
    private List<IncidentEvent> getIncidentEvents(final int type, final long playerId) {
        return this.playerMap.computeIfAbsent(playerId, key -> new HashMap<>())
                .computeIfAbsent(type, key -> new ArrayList<>());
    }

    /**
     * Gets the count of incident events of a type.
     *
     * @param type The type value incident event count will be looked up for.
     * @return Count of incident events of the type.
     */
    public int getIncidentEventCount(final int type) {
        return this.typeMap.computeIfAbsent(type, key -> new ArrayList<>()).size();
    }

    /**
     * Gets the substitution on time for a player.
     *
     * @param playerId The player the substitution on time will be looked up for.
     * @return The substitution on time for a player.
     */
    public int getSubstitutionOnTime(final long playerId) {
        return getIncidentEventTime(Type.TYPE_SUBSTITUTION_ON, playerId);
    }

    /**
     * Gets the substitution off time for a player.
     *
     * @param playerId The player the substitution off time will be looked up for.
     * @return The substitution off time for a player.
     */
    public int getSubstitutionOffTime(final long playerId) {
        return getIncidentEventTime(Type.TYPE_SUBSTITUTION_OFF, playerId);
    }

    /**
     * Gets goal count for a player.
     *
     * @param playerId The player the goal count will be looked up for.
     * @return Goal count for a player.
     */
    public int getGoals(final long playerId) {
        return (int) getIncidentEvents(Type.TYPE_GOAL, playerId).stream()
                .filter(incidentEvent -> !incidentEvent.getQualifierTypes().contains(Type.TYPE_OWN_GOAL))
                .count();
    }

    /**
     * Gets assist count for a player.
     *
     * @param playerId The player the assist count will be looked up for.
     * @return assist count for a player.
     */
    public int getGoalAssists(final long playerId) {
        return getIncidentEvents(Type.TYPE_ASSIST, playerId).size();
    }

    /**
     * Gets own goal count for a player.
     *
     * @param playerId The player the own goal count will be looked up for.
     * @return Own goal count for a player.
     */
    public int getOwnGoals(final long playerId) {
        return (int) getIncidentEvents(Type.TYPE_GOAL, playerId).stream()
                .filter(incidentEvent -> incidentEvent.getQualifierTypes().contains(Type.TYPE_OWN_GOAL))
                .count();
    }

    /**
     * Gets the yellow card time for a player.
     *
     * @param playerId The player the yellow card time will be looked up for.
     * @return The yellow card time for a player.
     */
    public int getYellowCardTime(final long playerId) {
        final IncidentEvent incidentEvent = getIncidentEvent(Type.TYPE_CARD, Type.TYPE_CARD_YELLOW, playerId);
        return incidentEvent == null ? 0 : incidentEvent.getMinute() + 1;
    }

    /**
     * Gets the red card time for a player.
     *
     * @param playerId The player the red card time will be looked up for.
     * @return The red card time for a player.
     */
    public int getRedCardTime(final long playerId) {
        final IncidentEvent incidentEvent = getIncidentEvent(Type.TYPE_CARD, Type.TYPE_CARD_RED, playerId);
        return incidentEvent == null ? 0 : incidentEvent.getMinute() + 1;
    }

    /**
     * Gets an incident event of a type for a player.
     *
     * @param type     The type value of the event that will be looked up.
     * @param playerId The player the event will be looked up for.
     * @return Incident event of a type for a player.
     */
    private IncidentEvent getIncidentEvent(final int type, final long playerId) {
        final List<IncidentEvent> incidentEvents = getIncidentEvents(type, playerId);
        return incidentEvents.isEmpty() ? null : incidentEvents.get(0);
    }

    /**
     * Gets an incident event of a type and a qualifier type for a player.
     *
     * @param type          The type value of the event that will be looked up.
     * @param qualifierType The qualifier type of the event that will be looked up.
     * @param playerId      The player the event will be looked up for.
     * @return Incident event of a type and qualifier type for a player.
     */
    private IncidentEvent getIncidentEvent(final int type, final int qualifierType, final long playerId) {
        return getIncidentEvents(type, playerId).stream()
                .filter(incidentEvent -> incidentEvent.getQualifierTypes().contains(qualifierType))
                .findFirst().orElse(null);
    }

    /**
     * Gets the time of an incident event of a type for a player.
     *
     * @param type     The type value of the event that the time will be looked up for.
     * @param playerId The player the event time will be looked up for.
     * @return Time of an incident event of a type for a player.
     */
    private int getIncidentEventTime(final int type, final long playerId) {
        final IncidentEvent incidentEvent = getIncidentEvent(type, playerId);
        return incidentEvent == null ? 0 : incidentEvent.getMinute() + 1;
    }

}
