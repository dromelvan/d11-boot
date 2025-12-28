package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player match stat repository tests.
 */
class PlayerMatchStatRepositoryTests extends AbstractRepositoryTests<PlayerMatchStat, PlayerMatchStatRepository> {

    /**
     * Tests PlayerMatchStatRepository::findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime.
     */
    @Test
    @SuppressWarnings("checkstyle:LineLength")
    void testFindByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime() {
        final List<PlayerMatchStat> entities = getEntities();
        entities.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getMatch().getDatetime()));

        final Set<Player> players = entities.stream()
                .map(PlayerMatchStat::getPlayer).collect(Collectors.toSet());
        final Set<Season> seasons = entities.stream()
                .map(playerMatchStat -> playerMatchStat.getMatch().getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(players.size() > 1);
        assertFalse(seasons.isEmpty());

        for (final Player player : players) {
            for (final Season season : seasons) {
                final List<PlayerMatchStat> result =
                        getRepository().findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(player.getId(),
                                                                                                    season.getId());

                final List<PlayerMatchStat> expected = entities.stream()
                        .filter(playerMatchStat -> playerMatchStat.getPlayer().equals(player)
                                                   && playerMatchStat.getMatch().getMatchWeek().getSeason()
                                                                     .equals(season))
                        .toList();

                assertTrue(expected.size() > 1);

                assertNotNull(result);
                assertFalse(result.isEmpty());
                assertEquals(expected, result);
            }
        }

    }

    /**
     * Tests PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder.
     */
    @Test
    void testFindByMatchIdOrderByPositionSortOrder() {
        final List<PlayerMatchStat> entities = getEntities();
        entities.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()));

        final Set<Match> matches = entities.stream()
                .map(PlayerMatchStat::getMatch).collect(Collectors.toSet());

        assertTrue(matches.size() > 1);

        for (final Match match : matches) {
            final List<PlayerMatchStat> result = getRepository().findByMatchIdOrderByPositionSortOrder(match.getId());

            final List<PlayerMatchStat> expected = entities.stream()
                    .filter(playerMatchStat -> playerMatchStat.getMatch().equals(match))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

    /**
     * Tests PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder.
     */
    @Test
    void testFindByD11MatchIdOrderByPositionSortOrder() {
        final List<PlayerMatchStat> entities = getEntities();
        entities.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()));

        final Set<D11Match> d11Matches = entities.stream()
                .map(PlayerMatchStat::getD11Match).collect(Collectors.toSet());

        assertTrue(d11Matches.size() > 1);

        for (final D11Match d11Match : d11Matches) {
            final List<PlayerMatchStat> result =
                    getRepository().findByD11MatchIdOrderByPositionSortOrder(d11Match.getId());

            final List<PlayerMatchStat> expected = entities.stream()
                    .filter(playerMatchStat -> playerMatchStat.getD11Match().equals(d11Match))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

}
