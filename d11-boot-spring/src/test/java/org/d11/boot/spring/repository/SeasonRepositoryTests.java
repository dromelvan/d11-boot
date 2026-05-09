package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.SeasonWinners;
import org.d11.boot.spring.model.TeamSeasonStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Season repository tests.
 */
class SeasonRepositoryTests extends AbstractRepositoryTests<Season, SeasonRepository> {

    /**
     * D11 team season stat repository.
     */
    @Autowired
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * Team season stat repository.
     */
    @Autowired
    private TeamSeasonStatRepository teamSeasonStatRepository;

    /**
     * Player season stat repository.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Tests SeasonRepository::findFirstByOrderByDateDesc.
     */
    @Test
    void testFindFirstByOrderByDateDesc() {
        final Season currentSeason = getEntities().stream()
                .max(Comparator.comparing(Season::getDate))
                .orElse(null);
        assertNotNull(currentSeason);

        final Season result = getRepository().findFirstByOrderByDateDesc().orElse(null);
        assertNotNull(result);
        assertEquals(currentSeason, result);
    }

    /**
     * Tests SeasonRepository::findByOrderByDateDesc.
     */
    @Test
    void testFindByOrderByDateDesc() {
        final List<Season> seasons = getEntities();
        seasons.sort(Comparator.comparing(Season::getDate).reversed());

        final List<Season> result = getRepository().findByOrderByDateDesc();

        assertNotNull(result);
        assertEquals(seasons, result);
    }

    /**
     * Tests SeasonRepository::findAllSeasonWinners.
     */
    @Test
    void testFindAllSeasonWinners() {
        final List<Season> seasons = getEntities();
        seasons.sort(Comparator.comparing(Season::getDate).reversed());

        final List<SeasonWinners> result = getRepository().findAllSeasonWinners();

        assertNotNull(result);
        assertEquals(seasons.size(), result.size());

        for (int i = 0; i < seasons.size(); ++i) {
            final Season season = seasons.get(i);
            final SeasonWinners seasonWinners = result.get(i);

            assertEquals(season, seasonWinners.season());

            final Optional<D11TeamSeasonStat> d11TeamSeasonStat =
                    this.d11TeamSeasonStatRepository.findBySeasonIdOrderByRanking(season.getId()).stream()
                            .filter(stat -> stat.getRanking() == 1)
                            .findFirst();
            assertEquals(d11TeamSeasonStat.orElse(null), seasonWinners.d11TeamSeasonStat());

            final Optional<TeamSeasonStat> teamSeasonStat =
                    this.teamSeasonStatRepository.findBySeasonIdOrderByRanking(season.getId()).stream()
                            .filter(stat -> stat.getRanking() == 1)
                            .findFirst();
            assertEquals(teamSeasonStat.orElse(null), seasonWinners.teamSeasonStat());

            final Optional<PlayerSeasonStat> playerSeasonStat =
                    this.playerSeasonStatRepository.findBySeasonId(season.getId()).stream()
                            .filter(stat -> stat.getRanking() == 1)
                            .findFirst();
            assertEquals(playerSeasonStat.orElse(null), seasonWinners.playerSeasonStat());
        }
    }

}
