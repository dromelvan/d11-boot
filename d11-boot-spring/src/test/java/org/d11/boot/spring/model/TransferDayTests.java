package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer day tests.
 */
class TransferDayTests extends EasyRandomTests {

    /**
     * Tests TransferDay::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void isValid() {
        final TransferDay transferDay = generate(TransferDay.class);

        assertTrue(transferDay.isValid(), "TransferDay::isValid");

        transferDay.setTransferDayNumber(-1);
        assertFalse(transferDay.isValid(), "TransferDay::isValid transfer window number negative");
        transferDay.setTransferDayNumber(1);

        transferDay.setStatus(null);
        assertFalse(transferDay.isValid(), "TransferDay::isValid status null");
        transferDay.setStatus(Status.PENDING);

        transferDay.setDatetime(null);
        assertFalse(transferDay.isValid(), "TransferDay::isValid datetime null");
        transferDay.setDatetime(LocalDateTime.now());

        transferDay.setTransferWindow(null);
        assertFalse(transferDay.isValid(), "TransferDay::isValid transfer window null");
        transferDay.setTransferWindow(new TransferWindow());

        assertTrue(transferDay.isValid(), "TransferDay::isValid valid");
    }

    /**
     * Tests TransferDay::compareTo.
     */
    @Test
    void testCompareTo() {
        final List<TransferDay> TransferDays = generateList(TransferDay.class);
        final LocalDateTime localDateTime = LocalDateTime.now();
        for (final TransferDay TransferDay : TransferDays) {
            TransferDay.setDatetime(localDateTime.plusDays(TransferDays.indexOf(TransferDay)));
        }

        final List<TransferDay> sorted = new ArrayList<>(TransferDays);
        Collections.sort(sorted);

        TransferDays.sort(Comparator.comparing(TransferDay::getDatetime).reversed());

        assertEquals(TransferDays, sorted, "TransferDay::compareTo equals");
    }

}
