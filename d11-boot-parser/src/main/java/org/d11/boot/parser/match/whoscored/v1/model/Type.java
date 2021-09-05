package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * A type of incident event or incident event qualifier. Example:
 * "type":{
 *      "value":18,
 *      "displayName":"SubstitutionOff"
 * }
 */
@Data
public class Type {

    /**
     * Penalty type value.
     */
    public static final int TYPE_PENALTY = 9;
    /**
     * Goal type value.
     */
    public static final int TYPE_GOAL = 16;
    /**
     * Card type value.
     */
    public static final int TYPE_CARD = 17;
    /**
     * Substitution off type value.
     */
    public static final int TYPE_SUBSTITUTION_OFF = 18;
    /**
     * Substitution on type value.
     */
    public static final int TYPE_SUBSTITUTION_ON = 19;
    /**
     * Own goal type value.
     */
    public static final int TYPE_OWN_GOAL = 28;
    /**
     * Yellow card type value.
     */
    public static final int TYPE_CARD_YELLOW = 31;
    /**
     * Red card type value.
     */
    public static final int TYPE_CARD_RED = 33;

    /**
     * Type value.
     */
    private int value;
    /**
     * Type display name.
     */
    private String displayName;

}
