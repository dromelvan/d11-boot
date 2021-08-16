package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A player id name dictionary object in a WhoScored match page match centre data.
 * Contains a set of if:name pairs.
 */
@Slf4j
public class PlayerIdNameDictionary extends LinkedHashMap<String, String> {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Gets the name associated with a player id.
     *
     * @param playerId Id of the player whose name will be looked up.
     * @return The name of the player with the provided id or null if no such player exists.
     */
    public String getPlayerName(final String playerId) {
        return get(playerId);
    }

    /**
     * Gets the player id associated with a name.
     *
     * @param playerName Name of the player whose id will be looked up.
     * @return The id of the player with the provided name or null if no such player exists.
     */
    public String getPlayerId(final String playerName) {
        final List<Map.Entry<String, String>> entries = entrySet().stream()
                .filter(entry -> entry.getValue().equals(playerName))
                .collect(Collectors.toList());

        final boolean multipleEntriesFound = entries.size() > 1;
        if(multipleEntriesFound) {
            final String ids = entries.stream()
                    .map(Map.Entry::getKey)
                    .collect(Collectors.joining(", "));
            log.warn("Player id dictionary contained {} entries for player name {}: {}", entries.size(), playerName, ids);
        } else if(entries.isEmpty()) {
            return null;
        }
        return entries.get(0).getKey();
    }
}
