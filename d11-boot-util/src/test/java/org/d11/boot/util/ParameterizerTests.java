package org.d11.boot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Parameterize tests.
 */
class ParameterizerTests {

    /**
     * Tests Parameterizer::parameterize.
     */
    @Test
    void testParameterize() {
        assertEquals("first-last", Parameterizer.parameterize("First Last"));
        assertEquals("jordao", Parameterizer.parameterize("Jordão"));
        assertEquals("bonds-n-gala", Parameterizer.parameterize("Bonds N'Gala"));
        assertEquals("daniel-tozer", Parameterizer.parameterize("Daniel Tözer"));
        assertEquals("pascal-gross", Parameterizer.parameterize("Pascal Groß"));
        assertEquals("shaun-wright-phillips", Parameterizer.parameterize("Shaun Wright-Phillips"));
        assertEquals("martin-odegaard", Parameterizer.parameterize("Martin Ødegaard"));
    }

}
