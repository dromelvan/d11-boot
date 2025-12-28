package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player season stat repository tests.
 */
class PlayerSeasonStatRepositoryTests extends AbstractRepositoryTests<PlayerSeasonStat, PlayerSeasonStatRepository> {

    /**
     * Tests PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc.
     */
    @Test
    void testFindByPlayerIdOrderBySeasonIdDesc() {
        final List<PlayerSeasonStat> entities = getEntities();
        entities.sort(Comparator.comparing(playerSeasonStat -> playerSeasonStat.getSeason().getId(),
                                           Comparator.reverseOrder()));

        final Set<Player> players = entities.stream()
                .map(PlayerSeasonStat::getPlayer).collect(Collectors.toSet());

        assertTrue(players.size() > 1);

        for (final Player player : players) {
            final List<PlayerSeasonStat> result = getRepository().findByPlayerIdOrderBySeasonIdDesc(player.getId());

            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findByPlayerIdAndSeasonId.
     */
    @Test
    void testFindByPlayerIdAndSeasonId() {
        final List<PlayerSeasonStat> entities = getEntities();

        assertFalse(entities.isEmpty());

        for (final PlayerSeasonStat entity : entities) {
            final Optional<PlayerSeasonStat> optional =
                    getRepository().findByPlayerIdAndSeasonId(entity.getPlayer().getId(), entity.getSeason().getId());

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(entity, result));
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking.
     */
    @Test
    @SuppressWarnings("checkstyle:LineLength")
    void testFindByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking() {
        final List<PlayerSeasonStat> entities = getEntities();
        entities.sort(Comparator.comparing(PlayerSeasonStat::getPosition).thenComparing(PlayerSeasonStat::getRanking));

        final Set<Team> teams = entities.stream().map(PlayerSeasonStat::getTeam).collect(Collectors.toSet());
        final Set<Season> seasons = entities.stream().map(PlayerSeasonStat::getSeason).collect(Collectors.toSet());

        assertTrue(teams.size() > 1);
        assertTrue(seasons.size() > 1);

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final List<PlayerSeasonStat> result = getRepository()
                        .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(team.getId(), season.getId());

                final List<PlayerSeasonStat> expected = entities.stream()
                        .filter(playerSeasonStat -> playerSeasonStat.getTeam().equals(team)
                                                    && playerSeasonStat.getSeason().equals(season))
                        .toList();

                assertFalse(expected.isEmpty());

                assertNotNull(result);
                assertFalse(result.isEmpty());
                assertEquals(expected, result);
            }
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking.
     */
    @Test
    @SuppressWarnings("checkstyle:LineLength")
    void testFindByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking() {
        final List<PlayerSeasonStat> entities = getEntities();
        entities.sort(Comparator.comparing(PlayerSeasonStat::getPosition).thenComparing(PlayerSeasonStat::getRanking));

        final Set<D11Team> d11Teams = entities.stream().map(PlayerSeasonStat::getD11Team).collect(Collectors.toSet());
        final Set<Season> seasons = entities.stream().map(PlayerSeasonStat::getSeason).collect(Collectors.toSet());

        assertTrue(d11Teams.size() > 1);
        assertTrue(seasons.size() > 1);

        for (final D11Team d11Team : d11Teams) {
            for (final Season season : seasons) {
                final List<PlayerSeasonStat> result = getRepository()
                        .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(d11Team.getId(), season.getId());

                final List<PlayerSeasonStat> expected = entities.stream()
                        .filter(playerSeasonStat -> playerSeasonStat.getD11Team().equals(d11Team)
                                                    && playerSeasonStat.getSeason().equals(season))
                        .toList();

                assertFalse(expected.isEmpty());

                assertNotNull(result);
                assertFalse(result.isEmpty());
                assertEquals(expected, result);
            }
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findBySeasonId.
     */
    @Test
    void testFindBySeasonId() {
        final List<PlayerSeasonStat> entities = getEntities();

        final Set<Season> seasons = entities.stream()
                .map(PlayerSeasonStat::getSeason).collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        for (final Season season : seasons) {
            final List<PlayerSeasonStat> result = getRepository().findBySeasonId(season.getId());

            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findBySeasonIdPaged.
     */
    @Test
    void testFindBySeasonIdPaged() {
        final List<PlayerSeasonStat> entities = getEntities();

        final Set<Season> seasons = entities.stream().map(PlayerSeasonStat::getSeason).collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        final int pageSize = 2;
        final String sortProperty = "ranking";

        for (final Season season : seasons) {
            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .sorted(Comparator.comparing(PlayerSeasonStat::getRanking))
                    .toList();

            final Pageable page1 = PageRequest.of(0, pageSize, Sort.by(sortProperty));
            final List<PlayerSeasonStat> page1Result = getRepository().findBySeasonId(season.getId(), page1);

            assertNotNull(page1Result);
            assertEquals(expected.subList(0, pageSize), page1Result);

            final Pageable page2 = PageRequest.of(1, pageSize, Sort.by(sortProperty));
            final List<PlayerSeasonStat> page2Result = getRepository().findBySeasonId(season.getId(), page2);

            assertNotNull(page2Result);
            assertEquals(expected.subList(pageSize, pageSize + 1), page2Result);
        }
    }

}
