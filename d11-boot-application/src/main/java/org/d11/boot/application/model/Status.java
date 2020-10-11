package org.d11.boot.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Entity status.
 */
@AllArgsConstructor
public enum Status {

    PENDING(0, "Pending"),
    ACTIVE(1, "Active"),
    FINISHED(2, "Finished"),
    FULL_TIME(3, "Full Time");

    /**
     * The id of the status.
     */
    @Getter
    private final int id;
    /**
     * The name of the status.
     */
    @Getter
    private final String name;

    /**
     * The name of the status.
     *
     * @return The name of the status.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets a status with a specified id.
     *
     * @param id The id of the status.
     * @return The status with the specified id or null if no such status exists.
     */
    public static Status withId(final int id) {
        for(final Status status : values()) {
            if(status.getId() == id) {
                return status;
            }
        }
        return null;
    }

}
