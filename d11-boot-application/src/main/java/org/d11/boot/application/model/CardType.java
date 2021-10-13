package org.d11.boot.application.model;

/**
 * Type of card in a Premier League match.
 */
public enum CardType {
    YELLOW(0, "Yellow"), RED(1, "Red");

    /**
     * Card type id.
     */
    private final int id;
    /**
     * Card type name.
     */
    private final String name;

    CardType(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get card type id.
     *
     * @return The card type id.
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the card type with the provided id.
     *
     * @param id Id of the card type that will be returned.
     * @return Card type with the provided id.
     */
    public static CardType withId(final int id) {
        switch (id) {
            case 0:
                return CardType.YELLOW;
            case 1:
                return CardType.RED;
            default:
                return null;
        }
    }

}
