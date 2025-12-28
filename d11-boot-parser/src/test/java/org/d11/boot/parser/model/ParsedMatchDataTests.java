package org.d11.boot.parser.model;

import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Parsed match data tests.
 */
class ParsedMatchDataTests {

    /**
     * Tests ParsedMatchData::isPending.
     */
    @Test
    void testIsPending() {
        final ParsedMatchData parsedMatchData = new ParsedMatchData();

        for (final Status status : Status.values()) {
            parsedMatchData.setStatus(status);
            assertEquals(Status.PENDING.equals(status), parsedMatchData.isPending());
        }
    }

    /**
     * Tests ParsedMatchData::isActive.
     */
    @Test
    void testIsActive() {
        final ParsedMatchData parsedMatchData = new ParsedMatchData();

        for (final Status status : Status.values()) {
            parsedMatchData.setStatus(status);
            assertEquals(Status.ACTIVE.equals(status), parsedMatchData.isActive());
        }
    }

    /**
     * Tests ParsedMatchData::isFullTime.
     */
    @Test
    void testIsFullTime() {
        final ParsedMatchData parsedMatchData = new ParsedMatchData();

        for (final Status status : Status.values()) {
            parsedMatchData.setStatus(status);
            assertEquals(Status.FULL_TIME.equals(status), parsedMatchData.isFullTime());
        }
    }

    /**
     * Tests ParsedMatchData::isPostponed.
     */
    @Test
    void testIsPostponed() {
        final ParsedMatchData parsedMatchData = new ParsedMatchData();

        for (final Status status : Status.values()) {
            parsedMatchData.setStatus(status);
            assertEquals(Status.POSTPONED.equals(status), parsedMatchData.isPostponed());
        }
    }

}
