package org.d11.boot.application.service.camel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.parser.model.MatchData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Context used to store data between different steps of a match update.
 */
@Data
@Slf4j
public class UpdateMatchContext {

    /**
     * Error message list.
     */
    private final List<String> errors = new ArrayList<>();
    /**
     * Update message list.
     */
    private final List<String> infos = new ArrayList<>();

    /**
     * Parsed match data from a match page.
     */
    private MatchData matchData;
    /**
     * Do/do not finish the match after update.
     */
    private boolean finish;
    /**
     * The match that will be updated.
     */
    private Match match;
    /**
     * Map of WhoScored id to player for players in the match.
     */
    private final Map<Integer, Player> players = new HashMap<>();
    /**
     * List of player season stats for players in the match.
     */
    private final List<PlayerSeasonStat> playerSeasonStats = new ArrayList<>();
    /**
     * List of player match stats for players in the match.
     */
    private final List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

    /**
     * Creates a new match update context.
     *
     * @param matchData Parsed match data from a match page.
     * @param finish    Do/do not finish the match after update.
     */
    public UpdateMatchContext(final MatchData matchData, final boolean finish) {
        this.matchData = matchData;
        this.finish = finish;
    }

    /**
     * Adds an info message.
     *
     * @param message The message that will be added.
     * @param values  Values that will be String.format()-ed into the message.
     */
    public void addInfo(final String message, final Object... values) {
        this.infos.add(String.format(message, values));
        log.info(String.format(message, values));
    }

    /**
     * Adds an error message.
     *
     * @param message The message that will be added.
     * @param values  Values that will be String.format()-ed into the message.
     */
    public void addError(final String message, final Object... values) {
        this.errors.add(String.format(message, values));
        log.error(String.format(message, values));
    }

    /**
     * The context has or doesn't have errors.
     *
     * @return True if the list of error messages is not empty, false if it is.
     */
    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    /**
     * Adds a player to the player map.
     *
     * @param player The player that will be added.
     */
    public void addPlayer(final Player player) {
        this.players.put(player.getWhoscoredId(), player);
    }

    /**
     * Gets a player with a specific WhoScored id.
     *
     * @param whoscoredId WhoScored id of the player that will be looked up.
     * @return The player with the specific WhoScored id.
     */
    public Player getPlayer(final int whoscoredId) {
        return this.players.get(whoscoredId);
    }

    /**
     * Adds a player season stat to the list of player season stats.
     *
     * @param playerSeasonStat The player season stat that will be added.
     */
    public void addPlayerSeasonStat(final PlayerSeasonStat playerSeasonStat) {
        this.playerSeasonStats.add(playerSeasonStat);
    }

    /**
     * Adds a player match stat to the list of player match stats.
     *
     * @param playerMatchStat The player match stat that will be added.
     */
    public void addPlayerMatchStat(final PlayerMatchStat playerMatchStat) {
        this.playerMatchStats.add(playerMatchStat);
    }

}
