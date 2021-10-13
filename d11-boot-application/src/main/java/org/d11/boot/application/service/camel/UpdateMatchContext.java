package org.d11.boot.application.service.camel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.MatchLogMessage;
import org.d11.boot.application.model.MatchLogMessageType;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerMatchStat;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.jms.model.MatchData;

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
     * List of match log messages.
     */
    private final List<MatchLogMessage> matchLogMessages = new ArrayList<>();
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
        final MatchLogMessage matchLogMessage = new MatchLogMessage();
        matchLogMessage.setMatch(this.match);
        matchLogMessage.setMatchLogMessageType(MatchLogMessageType.INFO);
        matchLogMessage.setMessage(String.format(message, values));
        this.matchLogMessages.add(matchLogMessage);
        log.info(matchLogMessage.getMessage());
    }

    /**
     * Adds an error message.
     *
     * @param message The message that will be added.
     * @param values  Values that will be String.format()-ed into the message.
     */
    public void addError(final String message, final Object... values) {
        final MatchLogMessage matchLogMessage = new MatchLogMessage();
        matchLogMessage.setMatch(this.match);
        matchLogMessage.setMatchLogMessageType(MatchLogMessageType.ERROR);
        matchLogMessage.setMessage(String.format(message, values));
        this.matchLogMessages.add(matchLogMessage);
        log.info(matchLogMessage.getMessage());
    }

    /**
     * The context has or doesn't have errors.
     *
     * @return True if the list of error messages is not empty, false if it is.
     */
    public boolean hasErrors() {
        return this.matchLogMessages.stream()
                .anyMatch(matchLogMessage -> matchLogMessage.getMatchLogMessageType().equals(MatchLogMessageType.ERROR));
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
