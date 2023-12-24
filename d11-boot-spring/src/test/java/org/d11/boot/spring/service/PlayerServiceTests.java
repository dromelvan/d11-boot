package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.util.Parameterizer;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Player service tests.
 */
class PlayerServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked player repository.
     */
    @Mock
    private PlayerRepository playerRepository;

    /**
     * Player service.
     */
    @InjectMocks
    private PlayerService playerService;

    /**
     * Tests PlayerService::searchByName.
     */
    @Test
    void testFindByName() {
        final String nameProperty = "name";

        final BadRequestException nullNameException =
                assertThrows(BadRequestException.class, () -> this.playerService.searchByName(null),
                             "PlayerService::searchByName null name throws");
        assertEquals(nameProperty, nullNameException.getParameter(),
                     "PlayerService::searchByName property equals null");

        final BadRequestException emptyNameException =
                assertThrows(BadRequestException.class, () -> this.playerService.searchByName(null),
                             "PlayerService::searchByName empty name throws");
        assertEquals(nameProperty, emptyNameException.getParameter(),
                     "PlayerService::searchByName property equals empty");

        final List<Player> players = generateList(Player.class);

        final String exact = "\"";

        for (final Player player : players) {
            final String likeName = Arrays.stream(player.getName().split("[ +]"))
                    .map(Parameterizer::parameterize)
                    .collect(Collectors.joining(PlayerService.SQL_LIKE));

            final PlayerSearchResult playerSearchResult = mock(PlayerSearchResult.class);

            when(this.playerRepository
                    .findByParameterizedNameLike(eq(PlayerService.SQL_LIKE + likeName + PlayerService.SQL_LIKE)))
                    .thenReturn(Collections.singletonList(playerSearchResult));

            final List<PlayerSearchResult> likeResult = this.playerService.searchByName(player.getName());

            assertEquals(1, likeResult.size(), "PlayerService::searchByName like size equals");
            assertEquals(playerSearchResult, likeResult.get(0), "PlayerService::searchByName like result equals");

            // Update mocked parameterized name since it won't be correct otherwise
            player.preUpdate();

            when(this.playerRepository
                    .findByParameterizedNameExact(eq(player.getParameterizedName())))
                    .thenReturn(Collections.singletonList(playerSearchResult));

            final List<PlayerSearchResult> exactResult =
                    this.playerService.searchByName(exact + player.getName() + exact);

            assertEquals(1, exactResult.size(), "PlayerService::searchByName exact size equals");
            assertEquals(playerSearchResult, exactResult.get(0), "PlayerService::searchByName exact result equals");

            verify(this.playerRepository, times(1))
                    .findByParameterizedNameLike(eq(PlayerService.SQL_LIKE + likeName + PlayerService.SQL_LIKE));
            verify(this.playerRepository, times(1)).findByParameterizedNameExact(eq(player.getParameterizedName()));
        }
    }

}
