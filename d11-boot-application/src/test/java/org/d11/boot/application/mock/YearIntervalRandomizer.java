package org.d11.boot.application.mock;

import org.jeasy.random.api.Randomizer;

import java.util.Random;

/**
 * Year interval randomizer.
 */
public class YearIntervalRandomizer implements Randomizer<String> {

    /**
     * The minimum year that will be randomized.
     */
    private static final int START_YEAR = 2000;
    /**
     * The interval years will be randomized inside.
     */
    private static final int YEAR_INTERVAL = 20;
    /**
     * A random that does the actual randomizing.
     */
    private static final Random RANDOM = new Random();

    @Override
    public String getRandomValue() {
        final int year = START_YEAR + RANDOM.nextInt(YEAR_INTERVAL);
        return String.format("%d-%d", year, year+1);
    }

}
