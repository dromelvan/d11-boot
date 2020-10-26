package org.d11.boot.application.util;

import java.text.Normalizer;
import java.util.Locale;

/**
 * Utility class for parameterizing names for easier searching.
 */
@SuppressWarnings("PMD.ClassNamingConventions")
public final class Parameterizer {

    /**
     * Utility class, should not be instantiated.
     */
    private Parameterizer() {}

    /**
     * Turns "Jordão N'Gala-Tözer" into "jordao-n-gala-tozer".
     *
     * @param string The string we want to parameterize.
     * @return The parameterized string.
     */
    public static String parameterize(final String string) {
        final String space = " ";
        String parameterizedName = string.toLowerCase(Locale.getDefault());
        parameterizedName = parameterizedName.replaceAll("['-]", space);
        parameterizedName = Normalizer.normalize(parameterizedName, Normalizer.Form.NFD);
        parameterizedName = parameterizedName.replaceAll("[^a-z ]", "").trim();
        parameterizedName = parameterizedName.replace(space, "-");
        return parameterizedName;
    }

}
