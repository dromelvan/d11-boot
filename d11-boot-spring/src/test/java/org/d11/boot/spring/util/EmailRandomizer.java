package org.d11.boot.spring.util;

import org.jeasy.random.api.Randomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;

/**
 * Randomizes email addresses.
 */
public class EmailRandomizer implements Randomizer<String> {

    /**
     * Max length of each part of the email.
     */
    private static final int EMAIL_COMPONENT_LENGTH = 5;

    /**
     * String randomizer that does the actual email part randomizing.
     */
    private static final StringRandomizer STRING_RANDOMIZER =
        new StringRandomizer(EMAIL_COMPONENT_LENGTH, EMAIL_COMPONENT_LENGTH);

    @Override
    public String getRandomValue() {
        return STRING_RANDOMIZER.getRandomValue() + "@"
            + STRING_RANDOMIZER.getRandomValue() + "."
            + STRING_RANDOMIZER.getRandomValue();
    }

}
