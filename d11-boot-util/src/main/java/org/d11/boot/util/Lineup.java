package org.d11.boot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Lineup status for a player match stat.
 */
@AllArgsConstructor
public enum Lineup {

    /**
     * The player did not participate in the match.
     */
    DID_NOT_PARTICIPATE(0, "Did not participate"),
    /**
     * The player started the match as a substitute.
     */
    SUBSTITUTE(1, "Substitute"),
    /**
     * The player was in the starting lineup.
     */
    STARTING_LINEUP(2, "Starting lineup");

    /**
     * Lineup id.
     */
    @Getter
    private final int id;

    /**
     * Lineup name.
     */
    @Getter
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gets a lineup by id.
     *
     * @param id The lineup id.
     * @return Lineup with the provided id.
     * @throws IllegalArgumentException If no lineup was found.
     */
    public static Lineup valueOfId(final int id) {
        return Arrays.stream(values())
                .filter(lineup -> lineup.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lineup with id " + id + " does not exist."));
    }

}
