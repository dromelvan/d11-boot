package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an incident event for a team for a match. Example:
 * {
 *      "id":2302235191.0,
 *      "eventId":230,
 *      "minute":26,
 *      "second":35,
 *      "teamId":161,
 *      "playerId":140088,
 *      "relatedEventId":231,
 *      "relatedPlayerId":77636,
 *      ...
 *      "type":{"value":18,"displayName":"SubstitutionOff"},
 *      ...
 *      "qualifiers":[ .. ],
 *      ...
 * }
 */
@Data
public class IncidentEvent {

    /**
     * Incident event id.
     */
    private double id;

    /**
     * Another incident event id.
     */
    private long eventId;

    /**
     * The minute the event occurred.
     */
    private int minute;

    /**
     * The second the event occurred.
     */
    private int second;

    /**
     * ID of the team for which the event occurred.
     */
    private long teamId;

    /**
     * ID of the player involved in the event.
     */
    private long playerId;

    /**
     * Related event id. For example a substitution on <-> substitution off.
     */
    private int relatedEventId;

    /**
     * If of the player involved in the related event.
     */
    private int relatedPlayerId;

    /**
     * Type of the event.
     */
    private Type type;

    /**
     * List of qualifiers for the event.
     */
    private List<Qualifier> qualifiers;

    /**
     * Returns a list of types for the qualifiers of the event.
     *
     * @return List of qualifier types.
     */
    public List<Integer> getQualifierTypes() {
        return this.qualifiers.stream()
                .map(qualifier -> qualifier.getType().getValue())
                .collect(Collectors.toList());
    }

}
