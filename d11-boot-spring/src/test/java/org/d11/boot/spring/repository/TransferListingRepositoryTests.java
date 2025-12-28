package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing repository tests.
 */
class TransferListingRepositoryTests extends AbstractRepositoryTests<TransferListing, TransferListingRepository> {

    /**
     * Tests TransferListingRepository::findByTransferDayIdAndPlayerId.
     */
    @Test
    void testFindByTransferDayIdAndPlayerId() {
        final List<TransferListing> entities = getEntities();

        assertFalse(entities.isEmpty());

        for (final TransferListing transferListing : entities) {
            final Optional<TransferListing> optional =
                    getRepository().findByTransferDayIdAndPlayerId(transferListing.getTransferDay().getId(),
                                                                   transferListing.getPlayer().getId());

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(transferListing, result));
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

        final int pageSize = 2;
        final String sortBy = "ranking";
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(sortBy));

        final List<TransferListing> result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                               pageable);

        assertNotNull(result);
        assertEquals(expected, result);

        for (final TransferListing transferListing : entities) {
            transferListing.setTransferDay(transferDay);
        }
        getRepository().saveAll(entities);

        final List<TransferListing> page1result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                                    pageable);
        assertNotNull(page1result);
        assertEquals(entities.subList(0, pageSize), page1result);

        pageable = PageRequest.of(1, pageSize, Sort.by(sortBy));

        final List<TransferListing> page2result = getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(),
                                                                                                    pageable);
        assertNotNull(page2result);
        assertEquals(entities.subList(pageSize, pageSize * 2), page2result);
    }

    /**
     * Tests TransferListingRepository::findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId.
     */
    @Test
    void testFindByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId() {
        final List<TransferListing> entities = getEntities();

        assertFalse(entities.isEmpty());

        final List<Season> seasons = entities.stream()
                .map(transferListing -> transferListing.getTransferDay().getTransferWindow().getMatchWeek().getSeason())
                .distinct()
                .toList();
        assertFalse(seasons.isEmpty());

        final List<D11Team> d11Teams = entities.stream()
                .map(TransferListing::getD11Team)
                .distinct()
                .toList();
        assertFalse(d11Teams.isEmpty());

        for (final Season season : seasons) {
            for (final D11Team d11Team : d11Teams) {
                final List<TransferListing> result = getRepository()
                        .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId());
                result.sort(Comparator.comparing(TransferListing::getId));

                final List<TransferListing> transferListings = entities.stream()
                        .filter(transferListing ->
                                transferListing.getTransferDay().getTransferWindow().getMatchWeek().getSeason()
                                        .equals(season)
                                && transferListing.getD11Team().equals(d11Team))
                        .sorted(Comparator.comparing(TransferListing::getId))
                        .toList();

                assertEquals(transferListings, result);
            }
        }
    }
}
