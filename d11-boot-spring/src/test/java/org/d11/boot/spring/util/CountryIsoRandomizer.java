package org.d11.boot.spring.util;

import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.api.Randomizer;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Country ISO "randomizer" that provides a unique country ISO. We're using this to avoid unlucky constraint violations
 * when running all tests in one go. We only actually get 26 * 26 unique codes. If that turns out to be not enough then
 * this randomizer has to be tweaked further.
 */
public class CountryIsoRandomizer implements Randomizer<String> {

    /**
     * Character array with uppercase A to Z chars.
     */
    private static final char[] LETTERS = IntStream.rangeClosed('A', 'Z')
            .mapToObj(obj -> (char) obj + StringUtils.EMPTY)
            .collect(Collectors.joining())
            .toCharArray();

    /**
     * The number of Country ISOs that have been generated.
     */
    private int count;

    @Override
    public String getRandomValue() {
        int index1 = count / LETTERS.length;
        int index2 = count % LETTERS.length;

        if (index1 >= LETTERS.length) {
            index1 = 0;
            index2 = 0;
            count = 0;
        }

        final String iso = String.format("%s%s", LETTERS[index1], LETTERS[index2]);

        ++count;

        return iso;
    }

}
