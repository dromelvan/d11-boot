package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerSearchResultDTO;
import org.d11.boot.api.model.SearchResultDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.client.api.SearchApi;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Search API tests.
 */
public class SearchApiTests extends AbstractApiTests<PlayerRepository> {

    /**
     * Tests the search operation.
     */
    @Test
    public void searchPlayer() {
        final SearchApi searchApi = getApi(SearchApi.class);
        SearchResultDTO searchResultDTO = searchApi.search("NOT_FOUND");

        assertNotNull(searchResultDTO, "Not found search result should not be null.");
        assertNotNull(searchResultDTO.getPlayers(), "Not found search result players should not be null.");
        assertTrue(searchResultDTO.getPlayers().isEmpty(), "Not found search result players should be empty.");

        // There are players named Bar Foo, Foo and Foo Bar available for this
        final List<Player> players = getRepository().findAll(Sort.by("firstName").and(Sort.by("lastName"))).stream()
                .filter(player -> player.getParameterizedName().matches(".*foo.*"))
                .collect(Collectors.toList());

        searchResultDTO = searchApi.search("Foo");
        assertEquals(players.size(), searchResultDTO.getPlayers().size(), "'Foo' should return 3 players.");
        for(int i = 0; i < players.size(); ++i) {
            assertPlayerEquals(players.get(i), searchResultDTO.getPlayers().get(i));
        }

        searchResultDTO = searchApi.search("Bar");
        assertEquals(2, searchResultDTO.getPlayers().size(), "'Bar' should return 2 players.");
        assertPlayerEquals(players.get(0), searchResultDTO.getPlayers().get(0));
        assertPlayerEquals(players.get(2), searchResultDTO.getPlayers().get(1));

        searchResultDTO = searchApi.search("Foo Bar");
        assertEquals(1, searchResultDTO.getPlayers().size(), "'Foo Bar' should return 1 player.");
        assertPlayerEquals(players.get(2), searchResultDTO.getPlayers().get(0));

        // Exact match search test
        searchResultDTO = searchApi.search("\"Foo\"");
        assertEquals(1, searchResultDTO.getPlayers().size(), "'\"Foo\"' should return 1 player.");
        assertPlayerEquals(players.get(1), searchResultDTO.getPlayers().get(0));
    }

    /**
     * Asserts that a player equals a player search result DTO.
     *
     * @param player The player that will be compared.
     * @param playerSearchResultDTO The player search result DTO that will be compared.
     */
    private void assertPlayerEquals(final Player player, final PlayerSearchResultDTO playerSearchResultDTO) {
        assertNotNull(player, "Player should not be null.");
        assertNotNull(playerSearchResultDTO, "Player search result DTO should not be null.");

        assertEquals(player.getId(), playerSearchResultDTO.getId(), "Player id should equal player search result id.");
        assertEquals(player.getName(), playerSearchResultDTO.getName(), "Player name should equal player search result name.");
        assertEquals(player.getPhotoFileName(), playerSearchResultDTO.getPhotoFileName(),
                "Player photo file name should equal player search result photo file name.");
    }
}
