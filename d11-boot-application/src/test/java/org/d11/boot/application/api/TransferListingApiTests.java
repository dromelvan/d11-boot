package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.DeleteTransferListingDTO;
import org.d11.boot.api.model.DeleteTransferListingResultDTO;
import org.d11.boot.api.model.InsertTransferListingDTO;
import org.d11.boot.api.model.InsertTransferListingResultDTO;
import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.client.api.TransferListingApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing API tests.
 */
public class TransferListingApiTests extends AbstractRepositoryApiTests<TransferListing, TransferListingRepository> {

    /**
     * Repository used to lookup the current season.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Sets up transfer days for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        Collections.sort(getEntities());
        assertFalse(getEntities().isEmpty(), "Transfer listings should not be empty.");
    }

    /**
     * Tests the findTransferListingById API operation.
     */
    @Test
    public void findTransferListingById() {
        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);
        for(final TransferListing transferListing : getRepository().findAll()) {
            final TransferListingDTO result = transferListingApi.findTransferListingById(transferListing.getId());
            final TransferListingDTO transferListingDTO = map(transferListing, TransferListingDTO.class);
            assertNotNull(result, "Transfer listing by id should not be null.");
            assertEquals(transferListingDTO, result, "Transfer listing by id should equal TransferListing.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> transferListingApi.findTransferListingById(-1L),
                     "Transfer day not found should throw NotFound exception.");
    }

    /**
     * Tests the findTransferListingByTransferDayId API operation.
     */
    @Test
    public void findTransferDayByTransferWindowId() {
        final Map<TransferDay, List<TransferListing>> transferDayMap = new HashMap<>();
        for(final TransferListing transferListing : getEntities()) {
            final List<TransferListing> transferListings = transferDayMap.computeIfAbsent(transferListing.getTransferDay(), p -> new ArrayList<>());
            transferListings.add(transferListing);
        }

        final TransferListingApi transferListingApi = getApi(TransferListingApi.class);
        for(final Map.Entry<TransferDay, List<TransferListing>> entry : transferDayMap.entrySet()) {
            final TransferDay transferDay = entry.getKey();
            final List<TransferListing> transferListings = entry.getValue();

            transferListings.sort(Comparator.comparing(TransferListing::getRanking));

            final List<TransferListingDTO> result = transferListingApi.findTransferListingByTransferDayId(transferDay.getId(), 0);

            assertNotNull(result, "Transfer listings by transfer day id should not be null.");
            assertEquals(map(transferListings, TransferListingDTO.class), result,
                    "Transfer listings by transfer day id should equal transfer listings.");
        }

        assertTrue(transferListingApi.findTransferListingByTransferDayId(-1L, 1).isEmpty(),
                "Transfer listings by transfer day id not found should be empty.");
    }

    /**
     * Tests the insertTransferListing API operation.
     */
    @Test
    public void insertTransferListing() {
        final InsertTransferListingDTO insertTransferListingDTO = new InsertTransferListingDTO();
        assertThrows(FeignException.BadRequest.class,
                () -> getApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO),
                "Insert transfer listing with invalid request throw BadRequest exception.");

        insertTransferListingDTO.setPlayerId(2L);

        assertThrows(FeignException.Forbidden.class,
                () -> getApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO),
                "Insert transfer listing not logged in should throw Forbidden exception.");

        assertThrows(FeignException.Forbidden.class,
                () -> getUserApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO),
                "Insert transfer listing as non admin for not owned D11 team should throw Forbidden exception.");

        insertTransferListingDTO.setPlayerId(1L);

        final InsertTransferListingResultDTO result = getUserApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO);

        assertNotNull(result, "Transfer listing as non admin should not return null.");
        assertEquals(insertTransferListingDTO.getPlayerId(), result.getPlayerId(),
                "Transfer listing as non admin result player id should equal input player id.");

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);
        final int count = getRepository().findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(2L, 1L).size();
        assertEquals(season.getMaxTransfers() - count, result.getRemainingTransfers(),
                "Result remaining transfers should equal season max transfers - current transfer listing count.");

        assertThrows(FeignException.BadRequest.class,
                () -> getUserApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO),
                "Insert transfer listing as non admin for already transfer listed player should throw BadRequest exception.");

        getRepository().deleteById(result.getTransferListingId());
    }

    /**
     * Tests the deleteTransferListing API operation.
     */
    @Test
    public void deleteTransferListing() {
        final long notOwnedPlayerId = 3L;

        final DeleteTransferListingDTO deleteTransferListingDTO = new DeleteTransferListingDTO();
        assertThrows(FeignException.BadRequest.class,
                () -> getApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO),
                "Delete transfer listing with invalid request throw BadRequest exception.");

        deleteTransferListingDTO.setPlayerId(notOwnedPlayerId);

        assertThrows(FeignException.Forbidden.class,
                () -> getApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO),
                "Delete transfer listing not logged in should throw Forbidden exception.");

        assertThrows(FeignException.BadRequest.class,
                () -> getUserApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO),
                "Delete transfer listing not transfer listed player should throw bad request exception.");

        final InsertTransferListingDTO insertTransferListingDTO = new InsertTransferListingDTO().playerId(notOwnedPlayerId);
        getAdministratorApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO);

        assertThrows(FeignException.BadRequest.class,
                () -> getUserApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO),
                "Delete transfer listing as non admin for not owned D11 team should throw bad request exception.");

        assertDoesNotThrow(() -> getAdministratorApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO),
                "Delete transfer listing as admin for not owned D11 team should not throw exception.");

        final long ownedPlayerId = 4L;
        insertTransferListingDTO.setPlayerId(ownedPlayerId);
        getUserApi(TransferListingApi.class).insertTransferListing(insertTransferListingDTO);

        deleteTransferListingDTO.setPlayerId(ownedPlayerId);

        final DeleteTransferListingResultDTO result = getUserApi(TransferListingApi.class).deleteTransferListing(deleteTransferListingDTO);
        assertNotNull(result, "Delete transfer listing as non admin should not return null.");
        assertEquals(deleteTransferListingDTO.getPlayerId(), result.getPlayerId(),
                "Delete transfer listing as non admin result player id should equal input player id.");

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);
        final int count = getRepository().findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), 1L).size();
        assertEquals(season.getMaxTransfers() - count, result.getRemainingTransfers(),
                "Delete result remaining transfers should equal season max transfers - current transfer listing count.");
    }

}
