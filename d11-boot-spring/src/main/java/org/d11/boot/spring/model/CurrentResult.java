package org.d11.boot.spring.model;

/**
 * Record holding the current season, match week, transfer window and transfer day.
 *
 * @param season           Current season, or null if none exists.
 * @param matchWeek        Current match week, or null if none exists.
 * @param transferWindow   Current transfer window, or null if none exists.
 * @param transferDay      Current transfer day, or null if none exists.
 */
public record CurrentResult(
        Season season,
        MatchWeek matchWeek,
        TransferWindow transferWindow,
        TransferDay transferDay
) {
}
