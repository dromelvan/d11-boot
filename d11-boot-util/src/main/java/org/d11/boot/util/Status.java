package org.d11.boot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Entity status.
 */
@Getter
@AllArgsConstructor
public enum Status {

    /**
     * The entity is pending and has not started yet.
     */
    PENDING(0, "PENDING"),
    /**
     * The entity is currently active.
     */
    ACTIVE(1, "ACTIVE"),
    /**
     * The entity is no longer active but has not yet been confirmed to be finished.
     */
    FULL_TIME(2, "FULL_TIME"),
    /**
     * The entity has finished.
     */
    FINISHED(3, "FINISHED"),
    /**
     * The entity has been postponed to an unknown date and time.
     */
    POSTPONED(4, "POSTPONED");

    /**
     * Status id.
     */
    private final int id;

    /**
     * Status name.
     */
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

    /**
     * Checks if a transition from this status to another status is valid.
     *
     * @param status The proposed new status.
     * @return True if a transition from this status to the proposed status is valid.
     */
    public boolean isValidTransition(final Status status) {
        if (Status.PENDING.equals(this)) {
            return Status.ACTIVE.equals(status) || Status.POSTPONED.equals(status);
        } else if (Status.ACTIVE.equals(this)) {
            return Status.FULL_TIME.equals(status) || Status.FINISHED.equals(status);
        } else if (Status.FULL_TIME.equals(this)) {
            return Status.FINISHED.equals(status);
        } else if (Status.POSTPONED.equals(this)) {
            return Status.PENDING.equals(status);
        }
        return false;
    }

}
