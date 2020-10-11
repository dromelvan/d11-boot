package org.d11.boot.application.mock;

import org.jeasy.random.FieldPredicates;

/**
 * Season random parameters.
 */
public class SeasonRandomParameters extends D11EntityRandomParameters {

    /**
     * Creates a new random parameters.
     */
    public SeasonRandomParameters() {
        randomize(FieldPredicates.named("name"), new YearIntervalRandomizer());
    }

}
