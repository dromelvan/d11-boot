package org.d11.boot.application.util;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Utility class for parameterizing names for easier searching.
 */
@SuppressWarnings("PMD.ClassNamingConventions")
public final class Parameterizer {

    /**
     * Map containing all characters that will be replaced.
     */
    private static final Map<String, String> REPLACE_ALL_MAP = new HashMap<>();

    static {
        REPLACE_ALL_MAP.put("ß", "ss");
        REPLACE_ALL_MAP.put("ø", "o");
        REPLACE_ALL_MAP.put("æ", "ae");
        REPLACE_ALL_MAP.put("['’-]", " ");
    }

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
        for(final Map.Entry<String, String> entry : REPLACE_ALL_MAP.entrySet()) {
            parameterizedName = parameterizedName.replaceAll(entry.getKey(), entry.getValue());
        }
        parameterizedName = Normalizer.normalize(parameterizedName, Normalizer.Form.NFD);
        parameterizedName = parameterizedName.replaceAll("[^a-z ]", "").trim();
        parameterizedName = parameterizedName.replace(space, "-");
        return parameterizedName;
    }

}
