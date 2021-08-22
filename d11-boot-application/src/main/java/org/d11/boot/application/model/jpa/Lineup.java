package org.d11.boot.application.model.jpa;

/**
 * Lineup status for a player match stat.
 */
public enum Lineup {
    DID_NOT_PARTICIPATE(0, "Did not participate"),
    SUBSTITUTE(1, "Substitute"),
    STARTING_LINEUP(2, "Starting lineup");

    /**
     * Lineup id.
     */
    private final int id;
    /**
     * Lineup name.
     */
    private final String name;

    Lineup(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get lineup id.
     *
     * @return The lineup id.
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the lineup with the provided id.
     *
     * @param id Id of the lineup that will be returned.
     * @return Lineup with the provided id.
     */
    @SuppressWarnings("checkstyle:ReturnCount")
    public static Lineup withId(final int id) {
        switch (id) {
            case 0:
                return Lineup.DID_NOT_PARTICIPATE;
            case 1:
                return Lineup.SUBSTITUTE;
            case 2:
                return Lineup.STARTING_LINEUP;
            default:
                return null;
        }
    }

}
