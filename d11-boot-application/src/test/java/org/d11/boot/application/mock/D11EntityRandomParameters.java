package org.d11.boot.application.mock;

import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

/**
 * Base entity random parameters.
 */
public class D11EntityRandomParameters extends EasyRandomParameters {

    /**
     * Max id that will be generated.
     */
    private static final long MAX_ID = 10_000;

    /**
     * Creates new random parameters.
     */
    public D11EntityRandomParameters() {
        randomize(FieldPredicates.named("id"), new LongRangeRandomizer(1L, MAX_ID));
    }

}
