package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
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

        assertTrue(players.size() > 1,
                   "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc players size > 1");

        for (final Player player : players) {
            final List<PlayerSeasonStat> result = getRepository().findByPlayerIdOrderBySeasonIdDesc(player.getId());

            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1,
                       "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc expected size > 1");

            assertNotNull(result, "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc not null ");
            assertFalse(result.isEmpty(), "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc empty");
            assertEquals(expected, result, "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc equals");
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

        assertTrue(seasons.size() > 1, "PlayerSeasonStatRepository::findBySeasonId seasons size > 1");

        for (final Season season : seasons) {
            final List<PlayerSeasonStat> result = getRepository().findBySeasonId(season.getId());

            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1, "PlayerSeasonStatRepository::findBySeasonId expected size > 1");

            assertNotNull(result, "PlayerSeasonStatRepository::findBySeasonId not null ");
            assertFalse(result.isEmpty(), "PlayerSeasonStatRepository::findBySeasonId empty");
            assertEquals(expected, result, "PlayerSeasonStatRepository::findBySeasonId equals");
        }
    }

    /**
     * Tests PlayerSeasonStatRepository::findBySeasonIdPaged.
     */
    @Test
    void testFindBySeasonIdPaged() {
        final List<PlayerSeasonStat> entities = getEntities();

        final Set<Season> seasons = entities.stream().map(PlayerSeasonStat::getSeason).collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "PlayerSeasonStatRepository::findBySeasonIdPaged seasons size > 1");

        final int pageSize = 2;
        final String sortProperty = "ranking";

        for (final Season season : seasons) {
            final List<PlayerSeasonStat> expected = entities.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .sorted(Comparator.comparing(PlayerSeasonStat::getRanking))
                    .toList();

            final Pageable page1 = PageRequest.of(0, pageSize, Sort.by(sortProperty));
            final List<PlayerSeasonStat> page1Result = getRepository().findBySeasonId(season.getId(), page1);

            assertNotNull(page1Result, "PlayerSeasonStatRepository::findBySeasonIdPaged page 1 not null");
            assertEquals(expected.subList(0, pageSize), page1Result,
                         "PlayerSeasonStatRepository::findBySeasonIdPaged page 1 equals");

            final Pageable page2 = PageRequest.of(1, pageSize, Sort.by(sortProperty));
            final List<PlayerSeasonStat> page2Result = getRepository().findBySeasonId(season.getId(), page2);

            assertNotNull(page2Result, "PlayerSeasonStatRepository::findBySeasonIdPaged page 2 not null");
            assertEquals(expected.subList(pageSize, pageSize + 1), page2Result,
                         "PlayerSeasonStatRepository::findBySeasonIdPaged page 2 equals");
        }
    }

}
