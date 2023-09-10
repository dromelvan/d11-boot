package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing repository tests.
 */
class TransferListingRepositoryTests extends D11BootRepositoryTests<TransferListing, TransferListingRepository> {

    /**
     * Creates new transfer listing repository tests.
     */
    TransferListingRepositoryTests() {
        super(TransferListing.class);
    }

    @Override
    protected void beforeSave(final TransferListing transferListing) {
        super.beforeSave(transferListing);
        transferListing.getTransferDay().setId(null);
        transferListing.getTransferDay().getTransferWindow().setId(null);
        transferListing.getTransferDay().getTransferWindow().getMatchWeek().setId(null);
        transferListing.getTransferDay().getTransferWindow().getMatchWeek().getSeason().setId(null);
        transferListing.getPlayer().setId(null);
        transferListing.getPlayer().getCountry().setId(null);
        transferListing.getTeam().setId(null);
        transferListing.getTeam().getStadium().setId(null);
        transferListing.getD11Team().setId(null);
        transferListing.getD11Team().getOwner().setId(null);
        transferListing.getD11Team().getCoOwner().setId(null);
        transferListing.getPosition().setId(null);
    }

    /**
     * Tests TransferListingRepository::findByTransferDayIdAndPlayerId.
     */
    @Test
    void testFindByTransferDayIdAndPlayerId() {
        final List<TransferListing> entities = getEntities();

        assertFalse(entities.isEmpty(), "TransferListingRepository::findByTransferDayIdAndPlayerId empty");

        for (final TransferListing transferListing : entities) {
            final Optional<TransferListing> optional =
                    getRepository().findByTransferDayIdAndPlayerId(transferListing.getTransferDay().getId(),
                                                                   transferListing.getPlayer().getId());

            assertTrue(optional.isPresent(), "TransferListingRepository::findByTransferDayIdAndPlayerId present");
            optional.ifPresent(result ->
                                   assertEquals(transferListing, result,
                                                "TransferListingRepository::findByTransferDayIdAndPlayerId equals"));
        }
    }

    /**
     * Tests TransferListingRepository::findByTransferDayIdOrderByRanking.
     */
    @Test
    void testFindByTransferDayIdOrderByRanking() {
        final List<TransferListing> entities = getEntities();
        // Set transfer day for two entities to the same one. This way the query should return some but not all the
        // entities
        final TransferDay transferDay = entities.get(0).getTransferDay();
        entities.get(1).setTransferDay(transferDay);

        for (final TransferListing transferListing : entities) {
            transferListing.setRanking(entities.indexOf(transferListing) + 1);
        }
        getRepository().saveAll(entities);

        final List<TransferListing> expected = entities.subList(0, 2);

        final int pageSize = 5;
        final String sortBy = "ranking";
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(sortBy));

        final List<TransferListing> result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                               pageable);

        assertNotNull(result, "TransferListingRepository::findByTransferDayIdOrderByRanking not null");
        assertEquals(expected, result, "TransferListingRepository::findByTransferDayIdOrderByRanking equals");

        for (final TransferListing transferListing : entities) {
            transferListing.setTransferDay(transferDay);
        }
        getRepository().saveAll(entities);

        final List<TransferListing> page1result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                                    pageable);
        assertNotNull(page1result, "TransferListingRepository::findByTransferDayIdOrderByRanking page 1 not null");
        assertEquals(entities.subList(0, pageSize), page1result,
                     "TransferListingRepository::findByTransferDayIdOrderByRanking page 1 equals");

        pageable = PageRequest.of(1, pageSize, Sort.by(sortBy));

        final List<TransferListing> page2result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                                    pageable);
        assertNotNull(page2result, "TransferListingRepository::findByTransferDayIdOrderByRanking page 2 not null");
        assertEquals(entities.subList(pageSize, pageSize * 2), page2result,
                     "TransferListingRepository::findByTransferDayIdOrderByRanking page 2 equals");
    }

}
