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

        assertFalse(fooResult.isEmpty(), "PlayerRepository::findByParameterizedNameLike foo isEmpty");
        assertEquals(fooPlayers.size(), fooResult.size(),
                     "PlayerRepository::findByParameterizedNameLike foo size equals");

        for (int i = 0; i < fooPlayers.size(); ++i) {
            assertEquals(fooPlayers.get(i).getId(), fooResult.get(i).getId(),
                         "PlayerRepository::findByParameterizedNameLike foo id equals");
            assertEquals(fooPlayers.get(i).getName(), fooResult.get(i).getName(),
                         "PlayerRepository::findByParameterizedNameLike foo name equals");
            assertTrue(fooResult.get(i).getTeamId() > 0,
                       "PlayerRepository::findByParameterizedNameLike foo teamId greaterThan");
            assertTrue(StringUtils.isNotBlank(fooResult.get(i).getTeamName()),
                       "PlayerRepository::findByParameterizedNameLike foo teamName not blank");
        }

        final List<Player> barPlayers = getRepository().findAll().stream()
               .filter(player -> player.getParameterizedName().matches(".*bar.*"))
               .sorted(Comparator.comparing(Player::getName))
               .toList();

        final List<PlayerSearchResult> barResult = getRepository().findByParameterizedNameLike("bar");

        assertFalse(barResult.isEmpty(), "PlayerRepository::findByParameterizedNameLike bar isEmpty");
        assertEquals(barPlayers.size(), barResult.size(),
                     "PlayerRepository::findByParameterizedNameLike bar size equals");

        for (int i = 0; i < barPlayers.size(); ++i) {
            assertEquals(barPlayers.get(i).getId(), barResult.get(i).getId(),
                         "PlayerRepository::findByParameterizedNameLike bar id equals");
            assertEquals(barPlayers.get(i).getName(), barResult.get(i).getName(),
                         "PlayerRepository::findByParameterizedNameLike bar name equals");
            assertTrue(barResult.get(i).getTeamId() > 0,
                       "PlayerRepository::findByParameterizedNameLike bar teamId greaterThan");
            assertTrue(StringUtils.isNotBlank(barResult.get(i).getTeamName()),
                       "PlayerRepository::findByParameterizedNameLike bar teamName not blank");
        }

        final List<Player> fooBarPlayers = getRepository().findAll().stream()
               .filter(player -> player.getParameterizedName().matches(".*foo-bar.*"))
               .sorted(Comparator.comparing(Player::getName))
               .toList();

        final List<PlayerSearchResult> fooBarResult = getRepository().findByParameterizedNameLike("foo-bar");

        assertFalse(fooBarResult.isEmpty(), "PlayerRepository::findByParameterizedNameLike foo-bar size greaterThan");
        assertEquals(fooBarPlayers.size(), fooBarResult.size(),
                     "PlayerRepository::findByParameterizedNameLike foo-bar size equals");

        for (int i = 0; i < fooBarPlayers.size(); ++i) {
            assertEquals(fooBarPlayers.get(i).getId(), fooBarResult.get(i).getId(),
                         "PlayerRepository::findByParameterizedNameLike foo-bar id equals");
            assertEquals(fooBarPlayers.get(i).getName(), fooBarResult.get(i).getName(),
                         "PlayerRepository::findByParameterizedNameLike foo-bar name equals");
            assertTrue(fooBarResult.get(i).getTeamId() > 0,
                       "PlayerRepository::findByParameterizedNameLike foo-bar teamId greaterThan");
            assertTrue(StringUtils.isNotBlank(fooBarResult.get(i).getTeamName()),
                       "PlayerRepository::findByParameterizedNameLike foo-bar teamName not blank");
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

        assertFalse(players.isEmpty(), "PlayerRepository::findByParameterizedNameExact players isEmpty");

        for (final Player player : players) {
            final List<PlayerSearchResult> result =
                    getRepository().findByParameterizedNameExact(player.getParameterizedName());

            assertEquals(1, result.size(), "PlayerRepository::findByParameterizedNameExact size isEquals");

            assertEquals(player.getId(), result.get(0).getId(),
                         "PlayerRepository::findByParameterizedNameExact id equals");
            assertEquals(player.getName(), result.get(0).getName(),
                         "PlayerRepository::findByParameterizedNameExact name equals");
            assertTrue(result.get(0).getTeamId() > 0,
                       "PlayerRepository::findByParameterizedNameExact teamId greaterThan");
            assertTrue(StringUtils.isNotBlank(result.get(0).getTeamName()),
                       "PlayerRepository::findByParameterizedNameExact teamName not blank");
        }
    }

}
