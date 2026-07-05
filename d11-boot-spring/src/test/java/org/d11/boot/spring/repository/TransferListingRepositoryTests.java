package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing repository tests.
 */
class TransferListingRepositoryTests extends AbstractRepositoryTests<TransferListing, TransferListingRepository> {

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Tests TransferListingRepository::saveAndFlush with duplicate playerId and transferDayId.
     */
    @Test
    void testSaveAndFlushUniqueConstraint() {
        final List<TransferListing> entities = getEntities();
        assertFalse(entities.isEmpty());

        final TransferListing transferListing = entities.get(0);
        final TransferListing duplicate = new TransferListing();

        duplicate.setTransferDay(transferListing.getTransferDay());
        duplicate.setPlayer(transferListing.getPlayer());
        duplicate.setTeam(transferListing.getTeam());
        duplicate.setD11Team(transferListing.getD11Team());
        duplicate.setPosition(transferListing.getPosition());

        assertThrows(DataIntegrityViolationException.class, () -> getRepository().saveAndFlush(duplicate));
    }

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

        for (final TransferListing transferListing : entities) {
            transferListing.setRanking(entities.indexOf(transferListing) + 1);
        }
        getRepository().saveAll(entities);

        final int pageSize = 2;
        final String sortBy = "ranking";

        int minPageCount = pageSize;
        int maxPageCount = 0;

        for (final TransferDay transferDay : this.transferDayRepository.findAll()) {
            final List<TransferListing> transferDayEntities = entities.stream()
                    .filter(transferListing -> transferListing.getTransferDay().equals(transferDay))
                    .sorted(Comparator.comparing(TransferListing::getRanking))
                    .toList();

            final int pages = transferDayEntities.size() / pageSize;

            if (pages < minPageCount) {
                minPageCount = pages;
            }
            if (pages > maxPageCount) {
                maxPageCount = pages;
            }

            for (int i = 0; i < pages; i++) {
                final List<TransferListing> expected = transferDayEntities.subList(i * pageSize,
                                                                                   i * pageSize + pageSize);
                final Pageable pageable = PageRequest.of(i, pageSize, Sort.by(sortBy));

                final List<TransferListing> result =
                        getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), null, pageable);

                assertNotNull(result);
                assertEquals(expected, result);
            }
        }

        // Make sure the test data has empty result and 2+ pages result
        assertEquals(0, minPageCount);
        assertTrue(maxPageCount >= pageSize);
    }

    /**
     * Tests TransferListingRepository::findByTransferDayIdOrderByRanking dummy filter.
     */
    @Test
    void testFindByTransferDayIdOrderByRankingDummyFilter() {
        final List<TransferListing> entities = getEntities();

        final Set<TransferDay> transferDays = entities.stream()
                .map(TransferListing::getTransferDay)
                .collect(Collectors.toSet());

        assertTrue(transferDays.size() > 1);

        final Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("ranking"));

        final List<TransferListing> combined = new ArrayList<>();

        for (final TransferDay transferDay : transferDays) {
            final List<TransferListing> transferListings = entities.stream()
                    .filter(tl -> tl.getTransferDay().equals(transferDay))
                    .sorted(Comparator.comparing(TransferListing::getRanking))
                    .toList();

            final List<TransferListing> dummyResult =
                    getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), Boolean.TRUE, pageable);

            assertNotNull(dummyResult);
            dummyResult.forEach(transferListing -> assertTrue(transferListing.getD11Team().isDummy()));

            final List<TransferListing> nonDummyResult =
                    getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), Boolean.FALSE, pageable);

            assertNotNull(nonDummyResult);
            nonDummyResult.forEach(transferListing -> assertFalse(transferListing.getD11Team().isDummy()));

            combined.addAll(dummyResult);
            combined.addAll(nonDummyResult);
            combined.sort(Comparator.comparing(TransferListing::getRanking));

            assertEquals(transferListings, combined);

            combined.clear();
        }
    }

    /**
     * Tests TransferListingRepository::findByTransferDayIdOrderByRanking unpaged.
     */
    @Test
    void testFindByTransferDayIdOrderByRankingUnpaged() {
        final List<TransferListing> entities = getEntities();

        final Set<TransferDay> transferDays = entities.stream()
                .map(TransferListing::getTransferDay)
                .collect(Collectors.toSet());

        assertTrue(transferDays.size() > 1);

        for (final TransferDay transferDay : transferDays) {
            final List<TransferListing> expected = entities.stream()
                    .filter(tl -> tl.getTransferDay().equals(transferDay))
                    .sorted(Comparator.comparing(TransferListing::getRanking))
                    .toList();

            final List<TransferListing> result =
                    getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), null);

            assertNotNull(result);
            assertEquals(expected, result);
        }
    }

    /**
     * Tests TransferListingRepository::findByTransferDayIdOrderByRanking unpaged dummy filter.
     */
    @Test
    void testFindByTransferDayIdOrderByRankingUnpagedDummyFilter() {
        final List<TransferListing> entities = getEntities();

        final Set<TransferDay> transferDays = entities.stream()
                .map(TransferListing::getTransferDay)
                .collect(Collectors.toSet());

        assertTrue(transferDays.size() > 1);

        final List<TransferListing> combined = new ArrayList<>();

        for (final TransferDay transferDay : transferDays) {
            final List<TransferListing> transferListings = entities.stream()
                    .filter(tl -> tl.getTransferDay().equals(transferDay))
                    .sorted(Comparator.comparing(TransferListing::getRanking))
                    .toList();

            final List<TransferListing> dummyResult =
                    getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), Boolean.TRUE);

            assertNotNull(dummyResult);
            dummyResult.forEach(transferListing -> assertTrue(transferListing.getD11Team().isDummy()));

            final List<TransferListing> nonDummyResult =
                    getRepository().findByTransferDayIdOrderByRanking(transferDay.getId(), Boolean.FALSE);

            assertNotNull(nonDummyResult);
            nonDummyResult.forEach(transferListing -> assertFalse(transferListing.getD11Team().isDummy()));

            combined.addAll(dummyResult);
            combined.addAll(nonDummyResult);
            combined.sort(Comparator.comparing(TransferListing::getRanking));

            assertEquals(transferListings, combined);

            combined.clear();
        }
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
