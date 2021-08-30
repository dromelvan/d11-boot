package org.d11.boot.application.model.jpa.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates a year interval to make sure it conforms to a \d{4}-\d{4} format and that the second integer is
 * one higher than the first.
 */
public class YearIntervalValidator implements ConstraintValidator<YearInterval, String> {

    /**
     * A \d{4}-\d{4} regexp pattern.
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("(\\d{4})-(\\d{4})");

    @Override
    public void initialize(final YearInterval yearInterval) {
        // No need for initialization
    }

    @Override
    public boolean isValid(final String yearInterval, final ConstraintValidatorContext constraintValidatorContext) {
        final Matcher matcher = NAME_PATTERN.matcher(yearInterval == null ? "" : yearInterval);
        if(matcher.matches()) {
            final int fromYear = Integer.parseInt(matcher.group(1));
            final int toYear = Integer.parseInt(matcher.group(2));
            return fromYear == toYear - 1;
        }
        return false;
    }

}
