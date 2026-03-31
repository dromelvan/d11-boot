package org.d11.boot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Player season stat sort order.
 */
@Getter
@AllArgsConstructor
public enum PlayerSeasonStatSort {

    /**
     * Sort by ranking, ascending.
     */
    RANKING("ranking"),

    /**
     * Sort by goals, descending.
     */
    GOALS("goals"),

    /**
     * Sort by rating, descending.
     */
    RATING("rating"),

    /**
     * Sort by form points, descending.
     */
    FORM("formPoints"),

    /**
     * Sort by points, descending.
     */
    POINTS("points");

    /**
     * The entity property name used for sorting.
     */
    private final String property;

}
