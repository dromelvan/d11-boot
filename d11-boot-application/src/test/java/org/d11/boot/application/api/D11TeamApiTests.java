package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.api.model.D11TeamNameDTO;
import org.d11.boot.api.model.D11TeamTransferStatusDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.client.api.D11TeamApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team API tests.
 */
public class D11TeamApiTests extends AbstractRepositoryApiTests<D11Team, D11TeamRepository> {

    /**
     * Repository for getting D11 team season stats to get control data for findD11TeamBySeasonId.
     */
    @Autowired
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;
    /**
     * Repository for looking up the current season.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Tests the findD11TeamById API operation.
     */
    @Test
    public void findD11TeamsById() {
        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);
        for(final D11Team d11Team : getRepository().findAll()) {
            final D11TeamDTO result = d11TeamApi.findD11TeamById(d11Team.getId());
            final D11TeamDTO d11TeamDTO = map(d11Team, D11TeamDTO.class);

            assertNotNull(result, "D11 team by id should not be null.");
            assertEquals(d11TeamDTO, result, "D11 team by id should equal D11 team.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> d11TeamApi.findD11TeamById(-1L),
                     "D11 team not found should throw NotFound exception.");
    }

    /**
     * Tests the findAllD11Teams API operation.
     */
    @Test
    public void findAllD11Teams() {
        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);
        final List<D11TeamNameDTO> result = d11TeamApi.findAllD11Teams();

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

        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);
        for(final Map.Entry<Season, List<D11Team>> entry : seasonMap.entrySet()) {
            final Season season = entry.getKey();
            final List<D11Team> d11Teams = entry.getValue();

            d11Teams.sort(Comparator.comparing(D11Team::getName));

            final List<D11TeamDTO> result = d11TeamApi.findD11TeamBySeasonId(season.getId());

            assertNotNull(result, "D11 teams by season id should not be null.");
            assertEquals(map(d11Teams, D11TeamDTO.class), result,
                    "D11 teams by season id should equal D11 teams.");
            assertTrue(d11TeamApi.findD11TeamBySeasonId(-1L).isEmpty(),
                    "D11 teams by invalid season id should be empty.");
        }
    }

    /**
     * Tests the findD11TeamsByCurrentSeason API operation.
     */
    @Test
    public void findD11TeamsByCurrentSeason() {
        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);
        final List<D11Team> d11Teams = this.d11TeamSeasonStatRepository.findAll().stream()
                .filter(d11TeamSeasonStat -> d11TeamSeasonStat.getSeason().equals(season))
                .map(D11TeamSeasonStat::getD11Team)
                .sorted(Comparator.comparing(D11Team::getName))
                .collect(Collectors.toList());

        final D11TeamApi d11TeamApi = getApi(D11TeamApi.class);
        final List<D11TeamDTO> result = d11TeamApi.findD11TeamBySeasonId(season.getId());

        assertNotNull(result, "D11 teams by current season should not be null.");
        assertEquals(map(d11Teams, D11TeamDTO.class), result,
                "D11 teams by current season should equal D11 teams.");
    }

    /**
     * Tests the findD11TeamTransferStatusById operation.
     */
    @Test
    public void findD11TeamTransferStatusById() {
        final long teamOneId = 1L;
        final long teamTwoId = 2L;

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);

        final D11TeamTransferStatusDTO notOwnerTeamOne = getApi(D11TeamApi.class).findD11TeamTransferStatusById(teamOneId);
        assertEquals(season.getMaxTransfers(), notOwnerTeamOne.getRemainingTransfers(),
                "Not owner team one remaining transfers should equal season max transfers.");
        assertTrue(notOwnerTeamOne.getPendingTransferListings().isEmpty(),
                "Not owner team one transfer listings should be empty.");

        final D11TeamTransferStatusDTO ownerTeamOne = getUserApi(D11TeamApi.class).findD11TeamTransferStatusById(teamOneId);
        assertEquals(season.getMaxTransfers() - 1, ownerTeamOne.getRemainingTransfers(),
                "Owner team one remaining transfers should equal season max transfers minus 1.");
        assertFalse(ownerTeamOne.getPendingTransferListings().isEmpty(),
                "Owner team one transfer listings should not be empty.");
        assertEquals(ownerTeamOne.getPendingTransferListings().size(), 1,
                "Owner team one remaining transfers size should equal 1.");

        final D11TeamTransferStatusDTO notOwnerTeamTwo = getApi(D11TeamApi.class).findD11TeamTransferStatusById(teamTwoId);
        assertEquals(season.getMaxTransfers() - 1, notOwnerTeamTwo.getRemainingTransfers(),
                "Not owner team two remaining transfers should equal season max transfers minus 1.");
        assertTrue(notOwnerTeamTwo.getPendingTransferListings().isEmpty(),
                "Not owner team two transfer listings should be empty.");

        final D11TeamTransferStatusDTO ownerTeamTwo = getAdministratorApi(D11TeamApi.class).findD11TeamTransferStatusById(teamTwoId);
        assertEquals(season.getMaxTransfers() - 1, ownerTeamTwo.getRemainingTransfers(),
                "Owner team two remaining transfers should equal season max transfers minus 1.");
        assertTrue(ownerTeamTwo.getPendingTransferListings().isEmpty(),
                "Owner team two transfer listings should be empty.");
    }

}
