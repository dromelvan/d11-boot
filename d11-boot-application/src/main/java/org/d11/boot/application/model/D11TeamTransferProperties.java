package org.d11.boot.application.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to keep track of max bids and position counts for a D11 team squad.
 */
@Data
public class D11TeamTransferProperties {

    /**
     * Max number of players in a D11 team.
     */
    private static final int MAX_PLAYER_COUNT = 11;
    /**
     * Id of the D11 team.
     */
    private final Long id;
    /**
     * Current max bid for the D11 team.
     */
    private int maxBid;
    /**
     * Position counts for the D11 team.
     */
    private final Map<Position, Integer> positionCount = new HashMap<>();

    /**
     * Creates new D11 team transfer properties.
     *
     * @param d11Team The D11 team for which transfer properties will be created.
     */
    public D11TeamTransferProperties(final D11Team d11Team) {
        this.id = d11Team.getId();
        this.maxBid = d11Team.getMaxBid();
        d11Team.getCurrentPlayerSeasonStats().forEach(playerSeasonStat ->
                incrementPositionCount(playerSeasonStat.getPosition())
        );
    }

    /**
     * Decrements the max bid by a specific amount.
     *
     * @param amount The amount the max bid will be decremented by.
     */
    public void decrementMaxBid(final int amount) {
        this.maxBid -= amount;
    }

    /**
     * Gets the position count for a position.
     *
     * @param position The position for which count will be returned.
     * @return Number of players currently in the D11 team for the position.
     */
    public int getPositionCount(final Position position) {
        return this.positionCount.getOrDefault(position, 0);
    }

    /**
     * Increments position count for a position.
     *
     * @param position The position the count will be incremented for.
     */
    public void incrementPositionCount(final Position position) {
        this.positionCount.put(position, getPositionCount(position) + 1);
    }

    /**
     * Checks if a position is biddable.
     *
     * @param position The position that will be checked.
     * @return True if the position is biddable, false otherwise.
     */
    public boolean isBiddablePosition(final Position position) {
        return getPositionCount(position) < position.getMaxCount();
    }

    /**
     * Checks if the D11 team has 11 players.
     *
     * @return True if the D11 team has 11 players, false if not.
     */
    public boolean isFull() {
        return this.positionCount.values().stream().mapToInt(count -> count).sum() == MAX_PLAYER_COUNT;
    }

}
