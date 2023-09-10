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
 * Transfer window tests.
 */
class TransferWindowTests extends EasyRandomTests {

    /**
     * Tests TransferWindow::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void isValid() {
        final TransferWindow transferWindow = generate(TransferWindow.class);

        assertTrue(transferWindow.isValid(), "TransferWindow::isValid");

        transferWindow.setTransferWindowNumber(-1);
        assertFalse(transferWindow.isValid(), "TransferWindow::isValid transfer window number negative");
        transferWindow.setTransferWindowNumber(1);

        transferWindow.setStatus(null);
        assertFalse(transferWindow.isValid(), "TransferWindow::isValid status null");
        transferWindow.setStatus(Status.PENDING);

        transferWindow.setDatetime(null);
        assertFalse(transferWindow.isValid(), "TransferWindow::isValid datetime null");
        transferWindow.setDatetime(LocalDateTime.now());

        transferWindow.setMatchWeek(null);
        assertFalse(transferWindow.isValid(), "TransferWindow::isValid match week null");
        transferWindow.setMatchWeek(new MatchWeek());

        assertTrue(transferWindow.isValid(), "TransferWindow::isValid valid");
    }

    /**
     * Tests TransferWindow::compareTo.
     */
    @Test
    void testCompareTo() {
        final List<TransferWindow> transferWindows = generateList(TransferWindow.class);
        final LocalDateTime localDateTime = LocalDateTime.now();
        for (final TransferWindow transferWindow : transferWindows) {
            transferWindow.setDatetime(localDateTime.plusDays(transferWindows.indexOf(transferWindow)));
        }

        final List<TransferWindow> sorted = new ArrayList<>(transferWindows);
        Collections.sort(sorted);

        transferWindows.sort(Comparator.comparing(TransferWindow::getDatetime).reversed());

        assertEquals(transferWindows, sorted, "TransferWindow::compareTo equals");
    }

}
