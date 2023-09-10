package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Player repository tests.
 */
class PlayerRepositoryTests extends D11BootRepositoryTests<Player, PlayerRepository> {

    /**
     * Creates new Player repository tests.
     */
    PlayerRepositoryTests() {
        super(Player.class);
    }

    @Override
    protected void beforeSave(final Player player) {
        super.beforeSave(player);
        player.getCountry().setId(null);
    }

    /**
     * Tests PlayerRepository::findByWhoscoredId.
     */
    @Test
    void testFindByWhoscoredId() {
        final List<Player> players = getEntities();
        players.forEach(player -> player.setWhoscoredId(players.indexOf(player)));
        getRepository().saveAll(players);

        players.forEach(player -> {
            final Player result = getRepository().findByWhoscoredId(player.getWhoscoredId()).orElse(null);

            assertNotNull(result, "PlayerRepository::findByWhoscoredId not null");
            assertEquals(player.getId(), result.getId(), "PlayerRepository::findByWhoscoredId id");
        });
    }

    /**
     * Tests PlayerRepository::findByPremierLeagueId.
     */
    @Test
    void testFindByPremierLeagueId() {
        final List<Player> players = getEntities();
        players.forEach(player -> player.setPremierLeagueId(players.indexOf(player)));
        getRepository().saveAll(players);

        players.forEach(player -> {
            final Player result = getRepository().findByPremierLeagueId(player.getPremierLeagueId()).orElse(null);

            assertNotNull(result, "PlayerRepository::findByPremierLeagueId not null");
            assertEquals(player.getId(), result.getId(), "PlayerRepository::findByPremierLeagueId id");
        });
    }

    /**
     * Tests PlayerRepository::findByParameterizedName.
     */
    @Test
    void testFindByParameterizedName() {
        final List<Player> players = getEntities();

        players.forEach(player -> {
            final List<Player> result = getRepository().findByParameterizedName(player.getParameterizedName());

            assertNotNull(result, "PlayerRepository::findByParameterizedName not null");
            assertFalse(result.isEmpty(), "PlayerRepository::findByParameterizedName empty");

            result.forEach(resultPlayer ->
                                   assertEquals(player.getParameterizedName(),
                                                resultPlayer.getParameterizedName(),
                                                "PlayerRepository::findByParameterizedName parameterized name"));
        });
    }

}
