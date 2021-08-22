package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.api.model.D11TeamNameDTO;
import org.d11.boot.api.service.D11TeamApiService;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.D11TeamSeasonStat;
import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team API tests.
 */
public class D11TeamApiTests extends AbstractRepositoryApiTests<D11Team, D11TeamRepository, D11TeamApiService> {

    /**
     * Repository for getting D11 team season stats to get control data for findD11TeamBySeasonId.
     */
    @Autowired
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * Tests the findD11TeamById API operation.
     */
    @Test
    public void findD11TeamsById() {
        for(final D11Team d11Team : getRepository().findAll()) {
            final D11TeamDTO result = getApiService().findD11TeamById(d11Team.getId());
            final D11TeamDTO d11TeamDTO = map(d11Team, D11TeamDTO.class);

            assertNotNull(result, "D11 team by id should not be null.");
            assertEquals(d11TeamDTO, result, "D11 team by id should equal D11 team.");
        }

        assertNull(getApiService().findD11TeamById(-1L), "D11 team not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findAllD11Teams API operation.
     */
    @Test
    public void findAllD11Teams() {
        final List<D11TeamNameDTO> result = getApiService().findAllD11Teams();

        final List<D11TeamNameDTO> d11TeamNameDTOs = map(getEntities(), D11TeamNameDTO.class);
        d11TeamNameDTOs.sort(Comparator.comparing(D11TeamNameDTO::getName));

        assertNotNull(result, "All D11 teams should not be null.");
        assertEquals(d11TeamNameDTOs, result, "All D11 teams should equal D11 teams.");
    }

    /**
     * Tests the findD11TeamBySeasonId API operation.
     */
    @Test
    public void findD11TeamBySeasonId() {
        final Map<Season, List<D11Team>> seasonMap = new HashMap<>();
        for(final D11TeamSeasonStat d11TeamSeasonStat : this.d11TeamSeasonStatRepository.findAll()) {
            final List<D11Team> d11Teams = seasonMap.computeIfAbsent(d11TeamSeasonStat.getSeason(), s -> new ArrayList<>());
            d11Teams.add(d11TeamSeasonStat.getD11Team());
        }

        for(final Map.Entry<Season, List<D11Team>> entry : seasonMap.entrySet()) {
            final Season season = entry.getKey();
            final List<D11Team> d11Teams = entry.getValue();

            d11Teams.sort(Comparator.comparing(D11Team::getName));

            final List<D11TeamDTO> result = getApiService().findD11TeamBySeasonId(season.getId());

            assertNotNull(result, "D11 teams by season id should not be null.");
            assertEquals(map(d11Teams, D11TeamDTO.class), result,
                    "D11 teams by season id should equal D11 teams.");
            assertTrue(getApiService().findD11TeamBySeasonId(-1L).isEmpty(),
                    "D11 teams by invalid season id should be empty.");
        }
    }

}
