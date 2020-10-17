package org.d11.boot.application.mock;

import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Stadium;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

/**
 * Base entity random parameters.
 */
public class D11EntityRandomParameters extends EasyRandomParameters {

    /**
     * Max positive general integer that will be generated.
     */
    private static final int MAX_POSITIVE = 100_000;
    /**
     * Max id that will be generated.
     */
    private static final long MAX_ID = 10_000;
    /**
     * Min year that will be generated.
     */
    private static final int MIN_YEAR = Stadium.MIN_OPENED_YEAR;
    /**
     * Max year that will be generated.
     */
    private static final int MAX_YEAR = Stadium.MAX_OPENED_YEAR;

    /**
     * Creates new random parameters.
     */
    public D11EntityRandomParameters() {
        randomize(FieldPredicates.named("id"), new LongRangeRandomizer(1L, MAX_ID));

        randomize(FieldPredicates.named("capacity").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("opened").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(MIN_YEAR, MAX_YEAR));

        randomize(FieldPredicates.named("name").and(FieldPredicates.inClass(Season.class)), new YearIntervalRandomizer());
    }

}
