package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.TeamMatchWeekStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Team match week stat repository tests.
 */
class TeamMatchWeekStatRepositoryTests extends D11BootRepositoryTests<TeamMatchWeekStat, TeamMatchWeekStatRepository> {

    /**
     * Updates match weeks.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Creates new team match week stat repository tests.
     */
    TeamMatchWeekStatRepositoryTests() {
        super(TeamMatchWeekStat.class);
    }

    @Override
    protected void beforeSave(final TeamMatchWeekStat teamMatchWeekStat) {
        super.beforeSave(teamMatchWeekStat);
        teamMatchWeekStat.getMatchWeek().setId(null);
        teamMatchWeekStat.getMatchWeek().getSeason().setId(null);
        teamMatchWeekStat.getTeam().setId(null);
        teamMatchWeekStat.getTeam().getStadium().setId(null);
    }

    /**
     * Tests TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate.
     */
    @Test
    void testFindByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate() {
        final List<TeamMatchWeekStat> entities = getEntities();
        // Set season for all entities to the same one and the team for two entities to the same one. This way the query
        // should return some but not all the entities.
        final Season season = entities.get(0).getMatchWeek().getSeason();

        for (final TeamMatchWeekStat teamMatchWeekStat : entities) {
            teamMatchWeekStat.getMatchWeek().setSeason(season);
            this.matchWeekRepository.save(teamMatchWeekStat.getMatchWeek());
        }

        final Team team = entities.get(0).getTeam();
        entities.get(1).setTeam(team);

        getRepository().save(entities.get(0));

        final List<TeamMatchWeekStat> expected = entities.subList(0, 2);
        expected.sort(Comparator.comparing(teamMatchWeekStat -> teamMatchWeekStat.getMatchWeek().getDate()));

        final List<TeamMatchWeekStat> result =
                getRepository().findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(team.getId(), season.getId());

        assertNotNull(result,
                      "TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate not null");
        assertEquals(expected, result,
                     "TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate equals");
    }

}
