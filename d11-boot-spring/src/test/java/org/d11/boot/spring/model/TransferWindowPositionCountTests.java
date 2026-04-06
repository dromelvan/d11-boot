package org.d11.boot.spring.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer window position count tests.
 */
@DataJpaTest
@ActiveProfiles("test")
class TransferWindowPositionCountTests extends EasyRandomTests {

    /**
     * Position property name.
     */
    private static final String POSITION = "position";

    /**
     * Transfer window repository.
     */
    @Autowired
    private TransferWindowRepository transferWindowRepository;

    /**
     * Transfer listing repository.
     */
    @Autowired
    private TransferListingRepository transferListingRepository;

    /**
     * Transfer repository.
     */
    @Autowired
    private TransferRepository transferRepository;

    /**
     * Entity manager for clearing context.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Tests TransferWindow::getTransferWindowPositionCounts.
     */
    @Test
    @SuppressWarnings({ "checkstyle:NestedForDepth", "PMD.CognitiveComplexity" })
    void testGetTransferWindowPositionCounts() {
        final List<TransferWindow> transferWindows = this.transferWindowRepository.findAll();

        assertFalse(transferWindows.isEmpty());

        final Map<Position, Integer> transferListingCounts = new ConcurrentHashMap<>();
        final Map<Position, Integer> transferCounts = new ConcurrentHashMap<>();

        boolean hasResults = false;
        for (final TransferWindow transferWindow : transferWindows) {
            transferListingCounts.clear();
            transferCounts.clear();

            for (final TransferDay transferDay : transferWindow.getTransferDays()) {
                if (transferDay.getTransferDayNumber() == 1) {
                    for (final TransferListing transferListing : transferDay.getTransferListings()) {
                        if (!transferListing.getD11Team().isDummy()) {
                            transferListingCounts.merge(transferListing.getPosition(), 1, Integer::sum);
                        }
                    }
                }
                for (final Transfer transfer
                        : this.transferRepository
                        .findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDay.getId())) {
                    final Optional<TransferListing> optional =
                            this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(),
                                                                                          transfer.getPlayer().getId());
                    optional.ifPresent(transferListing ->
                        transferCounts.merge(transferListing.getPosition(), 1, Integer::sum)
                    );
                }
            }

            final Set<TransferWindowPositionCount> result = transferWindow.getTransferWindowPositionCounts();

            final Set<Position> resultPositions = result.stream()
                    .map(TransferWindowPositionCount::getPosition)
                    .collect(Collectors.toSet());

            transferListingCounts.keySet().forEach(position -> assertTrue(resultPositions.contains(position)));

            for (final TransferWindowPositionCount positionCount : result) {
                hasResults = true;
                assertEquals(transferWindow, positionCount.getTransferWindow());
                assertNotNull(positionCount.getPosition());
                assertEquals(transferListingCounts.getOrDefault(positionCount.getPosition(), 0),
                             positionCount.getTransferListingCount());
                assertEquals(transferCounts.getOrDefault(positionCount.getPosition(), 0),
                             positionCount.getTransferCount());
            }
        }

        assertTrue(hasResults);
    }

    /**
     * Tests TransferWindow::getTransferWindowPositionCounts with pending status.
     */
    @Test
    @DirtiesContext
    void testGetTransferWindowPositionCountsPending() {
        final TransferWindow transferWindow = this.transferWindowRepository.findById(1L).orElseThrow();

        assertFalse(transferWindow.getTransferWindowPositionCounts().isEmpty());

        for (final TransferWindowPositionCount positionCount : transferWindow.getTransferWindowPositionCounts()) {
            assertTrue(positionCount.getTransferListingCount() > 0);
            assertTrue(positionCount.getTransferCount() > 0);
        }

        transferWindow.setStatus(Status.PENDING);
        this.transferWindowRepository.save(transferWindow);
        this.transferWindowRepository.flush();
        this.entityManager.clear();

        final TransferWindow pendingTransferWindow = this.transferWindowRepository.findById(1L).orElseThrow();

        assertTrue(pendingTransferWindow.getTransferWindowPositionCounts().isEmpty());
    }

    /**
     * Tests TransferWindowPositionCount::compareTo.
     */
    @Test
    void testCompareTo() {
        final Position position1 = new Position().setSortOrder(1);
        final Position position2 = new Position().setSortOrder(2);
        final Position position3 = new Position().setSortOrder(3);

        final TransferWindowPositionCount transferWindowPositionCount1 = new TransferWindowPositionCount();
        ReflectionTestUtils.setField(transferWindowPositionCount1, POSITION, position1);
        final TransferWindowPositionCount transferWindowPositionCount2 = new TransferWindowPositionCount();
        ReflectionTestUtils.setField(transferWindowPositionCount2, POSITION, position2);
        final TransferWindowPositionCount transferWindowPositionCount3 = new TransferWindowPositionCount();
        ReflectionTestUtils.setField(transferWindowPositionCount3, POSITION, position3);

        final List<TransferWindowPositionCount> expected = List.of(transferWindowPositionCount1,
                                                                   transferWindowPositionCount2,
                                                                   transferWindowPositionCount3);

        final List<TransferWindowPositionCount> sorted = new ArrayList<>(List.of(transferWindowPositionCount3,
                                                                                 transferWindowPositionCount1,
                                                                                 transferWindowPositionCount2));
        Collections.sort(sorted);
        assertEquals(expected, sorted);
    }

}
