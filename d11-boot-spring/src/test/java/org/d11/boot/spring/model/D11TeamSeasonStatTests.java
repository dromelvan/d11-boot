package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team season stat tests.
 */
class D11TeamSeasonStatTests extends EasyRandomTests {

    /**
     * Tests D11TeamSeasonStat::isValid.
     */
    @Test
    void testIsValid() {
        final D11TeamSeasonStat d11TeamSeasonStat = generate(D11TeamSeasonStat.class);

        assertTrue(d11TeamSeasonStat.isValid());

        d11TeamSeasonStat.setWinCount(-1);
        assertFalse(d11TeamSeasonStat.isValid());
        d11TeamSeasonStat.setWinCount(1);

        d11TeamSeasonStat.setD11Team(null);
        assertFalse(d11TeamSeasonStat.isValid());
        d11TeamSeasonStat.setD11Team(new D11Team());

        d11TeamSeasonStat.setSeason(null);
        assertFalse(d11TeamSeasonStat.isValid());
        d11TeamSeasonStat.setSeason(new Season());

        assertTrue(d11TeamSeasonStat.isValid());
    }

}
