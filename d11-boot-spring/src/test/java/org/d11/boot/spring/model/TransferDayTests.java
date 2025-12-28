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
    void isValid() {
        final TransferDay transferDay = generate(TransferDay.class);

        assertTrue(transferDay.isValid());

        transferDay.setTransferDayNumber(-1);
        assertFalse(transferDay.isValid());
        transferDay.setTransferDayNumber(1);

        transferDay.setStatus(null);
        assertFalse(transferDay.isValid());
        transferDay.setStatus(Status.PENDING);

        transferDay.setDatetime(null);
        assertFalse(transferDay.isValid());
        transferDay.setDatetime(LocalDateTime.now());

        transferDay.setTransferWindow(null);
        assertFalse(transferDay.isValid());
        transferDay.setTransferWindow(new TransferWindow());

        assertTrue(transferDay.isValid());
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

        assertEquals(TransferDays, sorted);
    }

}
