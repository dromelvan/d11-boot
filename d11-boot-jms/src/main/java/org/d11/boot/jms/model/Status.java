package org.d11.boot.jms.model;

/**
 * Status for matches, match weeks, seasons, anything, really.
 */
public enum Status {

    /**
     * The status is pending.
     */
    PENDING,
    /**
     * The status is active.
     */
    ACTIVE,
    /**
     * The status s full time but not yet finalised.
     */
    FULL_TIME,
    /**
     * The status is finished and finalised.
     */
    FINISHED,
    /**
     * The status is postponed.
     */
    POSTPONED

}
