package org.d11.boot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Entity status.
 */
@AllArgsConstructor
public enum Status {

    /**
     * The entity is pending and has not started yet.
     */
    PENDING(0, "Pending"),
    /**
     * The entity is currently active.
     */
    ACTIVE(1, "Active"),
    /**
     * The entity is no longer active but has not yet been confirmed to be finished.
     */
    FULL_TIME(2, "Full Time"),
    /**
     * The entity has finished.
     */
    FINISHED(3, "Finished"),
    /**
     * The entity has been postponed to an unknown date and time.
     */
    POSTPONED(4, "Postponed");

    /**
     * Status id.
     */
    @Getter
    private final int id;

    /**
     * Status name.
     */
    @Getter
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets a status by id.
     *
     * @param id The status id.
     * @return Status with the specified id.
     * @throws IllegalArgumentException If no status was found.
     */
    public static Status valueOfId(final int id) {
        return Arrays.stream(values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status with id " + id + " does not exist."));
    }

}
