package org.d11.boot.spring.util;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.TransferDay;

/**
 * Property holder for tests.
 *
 * @param transferDay Transfer day.
 * @param player Player.
 */
public record TransferDayPlayer(TransferDay transferDay, Player player) { }
