package org.d11.boot.spring.util;

import org.jeasy.random.api.Randomizer;

/**
 * Year interval "randomizer" that provides a unique year interval.
 */
public class YearIntervalRandomizer implements Randomizer<String> {

    /**
     * The minimum starting year that will be generated.
     */
    private static final int STARTING_YEAR = 2000;

    /**
     * The number of year intervals that have been generated.
     */
    private static int COUNT;

    @Override
    public String getRandomValue() {
        final int year = STARTING_YEAR + getNextYear();
        return String.format("%d-%d", year, year + 1);
    }

    /**
     * Gets the next year.
     *
     * @return The next year.
     */
    private static int getNextYear() {
        return COUNT++;
    }

}
