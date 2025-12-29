package org.d11.boot.spring.repository;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player repository tests.
 */
class PlayerRepositoryTests extends AbstractRepositoryTests<Player, PlayerRepository> {

    /**
     * Tests PlayerRepository::findByStatSourceId.
     */
    @Test
    void testFindByStatSourceId() {
        final List<Player> players = getEntities();
        players.forEach(player -> player.setStatSourceId(players.indexOf(player)));
        getRepository().saveAll(players);

        players.forEach(player -> {
            final Player result = getRepository().findByStatSourceId(player.getStatSourceId()).orElse(null);

            assertNotNull(result);
            assertEquals(player.getId(), result.getId());
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

            assertNotNull(result);
            assertEquals(player.getId(), result.getId());
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

            assertNotNull(result);
            assertFalse(result.isEmpty());

            result.forEach(resultPlayer ->
                                   assertEquals(player.getParameterizedName(), resultPlayer.getParameterizedName()));
        });
    }

    /**
     * Tests PlayerRepository::findByParameterizedNameLike.
     */
    @Test
    void testFindByParameterizedNameLike() {
        // There are players named Bar Foo, Foo and Foo Bar available for this
        final List<Player> fooPlayers = getRepository().findAll().stream()
                .filter(player -> player.getParameterizedName().matches(".*foo.*"))
                .sorted(Comparator.comparing(Player::getName))
                .toList();

        final List<PlayerSearchResult> fooResult = getRepository().findByParameterizedNameLike("foo");

        assertFalse(fooResult.isEmpty());
        assertEquals(fooPlayers.size(), fooResult.size());

        for (int i = 0; i < fooPlayers.size(); ++i) {
            assertEquals(fooPlayers.get(i).getId(), fooResult.get(i).getId());
            assertEquals(fooPlayers.get(i).getName(), fooResult.get(i).getName());
            assertTrue(fooResult.get(i).getTeamId() > 0);
            assertTrue(StringUtils.isNotBlank(fooResult.get(i).getTeamName()));
        }

        final List<Player> barPlayers = getRepository().findAll().stream()
               .filter(player -> player.getParameterizedName().matches(".*bar.*"))
               .sorted(Comparator.comparing(Player::getName))
               .toList();

        final List<PlayerSearchResult> barResult = getRepository().findByParameterizedNameLike("bar");

        assertFalse(barResult.isEmpty());
        assertEquals(barPlayers.size(), barResult.size());

        for (int i = 0; i < barPlayers.size(); ++i) {
            assertEquals(barPlayers.get(i).getId(), barResult.get(i).getId());
            assertEquals(barPlayers.get(i).getName(), barResult.get(i).getName());
            assertTrue(barResult.get(i).getTeamId() > 0);
            assertTrue(StringUtils.isNotBlank(barResult.get(i).getTeamName()));
        }

        final List<Player> fooBarPlayers = getRepository().findAll().stream()
               .filter(player -> player.getParameterizedName().matches(".*foo-bar.*"))
               .sorted(Comparator.comparing(Player::getName))
               .toList();

        final List<PlayerSearchResult> fooBarResult = getRepository().findByParameterizedNameLike("foo-bar");

        assertFalse(fooBarResult.isEmpty());
        assertEquals(fooBarPlayers.size(), fooBarResult.size());

        for (int i = 0; i < fooBarPlayers.size(); ++i) {
            assertEquals(fooBarPlayers.get(i).getId(), fooBarResult.get(i).getId());
            assertEquals(fooBarPlayers.get(i).getName(), fooBarResult.get(i).getName());
            assertTrue(fooBarResult.get(i).getTeamId() > 0);
            assertTrue(StringUtils.isNotBlank(fooBarResult.get(i).getTeamName()));
        }
    }

    /**
     * Tests PlayerRepository::findByParameterizedNameExact.
     */
    @Test
    void testFindByParameterizedNameExact() {
        final List<Player> players = getRepository().findAll().stream()
               .sorted(Comparator.comparing(Player::getName))
               .toList();

        assertFalse(players.isEmpty());

        for (final Player player : players) {
            final List<PlayerSearchResult> result =
                    getRepository().findByParameterizedNameExact(player.getParameterizedName());

            assertEquals(1, result.size());

            assertEquals(player.getId(), result.get(0).getId());
            assertEquals(player.getName(), result.get(0).getName());
            assertTrue(result.get(0).getTeamId() > 0);
            assertTrue(StringUtils.isNotBlank(result.get(0).getTeamName()));
        }
    }

}
