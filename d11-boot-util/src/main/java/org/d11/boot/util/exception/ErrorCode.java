package org.d11.boot.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error codes with corresponding messages.
 */
@AllArgsConstructor
public enum ErrorCode {

    /**
     * Default error.
     */
    DEFAULT("An error occurred"),

    /**
     * Property is missing error.
     */
    BAD_REQUEST_INVALID_PARAMETER("is invalid"),

    /**
     * Property is missing error.
     */
    BAD_REQUEST_PROPERTY_IS_MISSING("is missing"),

    /**
     * Invalid transfer day status error.
     */
    CONFLICT_INVALID_TRANSFER_DAY_STATUS("Transfer day status is invalid"),

    /**
     * No current transfer dau error.
     */
    CONFLICT_NO_CURRENT_TRANSFER_DAY("Current transfer day does not exist"),

    /**
     * No current transfer window error.
     */
    CONFLICT_NO_CURRENT_TRANSFER_WINDOW("Current transfer window does not exist"),

    /**
     * No player season stat error.
     */
    CONFLICT_NO_PLAYER_SEASON_STAT("Player is not active in the current season"),

    /**
     * No player season stat error.
     */
    CONFLICT_NO_REMAINING_D11_TEAM_TRANSFERS("D11 team has no remaining transfers for the current season"),

    /**
     * Non unique transfer listing error.
     */
    CONFLICT_NON_UNIQUE_TRANSFER_LISTING("Player is already transfer listed"),

    /**
     * The current user is not allowed to bid on the player at this time.
     */
    CONFLICT_TRANSFER_BID_NOT_ALLOWED("Transfer bid is not allowed");


    /**
     * Error message.
     */
    @Getter
    private final String message;

    /**
     * Gets a code representation of the error.
     *
     * @return Code representation of the error.
     */
    public String getCode() {
        return this.name();
    }

}
