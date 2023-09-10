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
        assertEquals("first-last", Parameterizer.parameterize("First Last"),
                     "Parameterizer::parameterize First Last equals");
        assertEquals("jordao", Parameterizer.parameterize("Jordão"), "Parameterizer::parameterize Jordão equals");
        assertEquals("bonds-n-gala", Parameterizer.parameterize("Bonds N'Gala"),
                     "Parameterizer::parameterize Bonds N'Gala equals");
        assertEquals("daniel-tozer", Parameterizer.parameterize("Daniel Tözer"),
                     "Parameterizer::parameterize Daniel Tözer equals");
        assertEquals("pascal-gross", Parameterizer.parameterize("Pascal Groß"),
                     "Parameterizer::parameterize Pascal Groß equals");
        assertEquals("shaun-wright-phillips", Parameterizer.parameterize("Shaun Wright-Phillips"),
                     "Parameterizer::parameterize Shaun Wright-Phillips equals");
        assertEquals("martin-odegaard", Parameterizer.parameterize("Martin Ødegaard"),
                     "Parameterizer::parameterize Martin Ødegaard equals");
    }

}
