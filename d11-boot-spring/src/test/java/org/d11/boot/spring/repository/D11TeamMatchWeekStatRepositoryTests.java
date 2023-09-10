package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.D11TeamMatchWeekStat;
import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * D11 Team match week stat repository tests.
 */
class D11TeamMatchWeekStatRepositoryTests extends D11BootRepositoryTests<D11TeamMatchWeekStat,
                                                                         D11TeamMatchWeekStatRepository> {

    /**
     * Updates match weeks.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Creates new D11 team match week stat repository tests.
     */
    D11TeamMatchWeekStatRepositoryTests() {
        super(D11TeamMatchWeekStat.class);
    }

    @Override
    protected void beforeSave(final D11TeamMatchWeekStat d11TeamMatchWeekStat) {
        super.beforeSave(d11TeamMatchWeekStat);
        d11TeamMatchWeekStat.getMatchWeek().setId(null);
        d11TeamMatchWeekStat.getMatchWeek().getSeason().setId(null);
        d11TeamMatchWeekStat.getD11Team().setId(null);
        d11TeamMatchWeekStat.getD11Team().getOwner().setId(null);
        d11TeamMatchWeekStat.getD11Team().getCoOwner().setId(null);
    }

    /**
     * Tests D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate.
     */
    @Test
    void testFindByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate() {
        final List<D11TeamMatchWeekStat> entities = getEntities();
        // Set season for all entities to the same one and the D11 team for two entities to the same one. This way the
        // query should return some but not all the entities.
        final Season season = entities.get(0).getMatchWeek().getSeason();

        for (final D11TeamMatchWeekStat d11TeamMatchWeekStat : entities) {
            d11TeamMatchWeekStat.getMatchWeek().setSeason(season);
            this.matchWeekRepository.save(d11TeamMatchWeekStat.getMatchWeek());
        }

        final D11Team d11Team = entities.get(0).getD11Team();
        entities.get(1).setD11Team(d11Team);

        getRepository().save(entities.get(0));

        final List<D11TeamMatchWeekStat> expected = entities.subList(0, 2);
        expected.sort(Comparator.comparing(d11TeamMatchWeekStat -> d11TeamMatchWeekStat.getMatchWeek().getDate()));

        final List<D11TeamMatchWeekStat> result = getRepository()
                .findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(d11Team.getId(), season.getId());

        assertNotNull(result,
                      """
                      D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate not null
                      """);
        assertEquals(expected, result,
                     "D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate equals");
    }

}
