package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

import java.util.regex.Pattern;

/**
 * Represents the top level match centre data object in a WhoScored match page script element. Example:
 * require.config.params["args"] = {
 *     matchId:1485563,
 *     matchCentreData: {
 *          "playerIdNameDictionary": { .. },
 *          ...
 *          "elapsed: "FT",
 *          ...
 *          "home": { .. },
 *          "away": { .. },
 *          ...
 *    },
 *    matchCentreEventTypeJson: { .. },
 *    ...
 * }
 */
@Data
public class MatchCenterData {

    /**
     * Pattern for script element containing match centre data.
     */
    public static final Pattern MATCH_CENTRE_DATA_PATTERN = Pattern.compile(".*require.config.params\\[\"args\"] = \\{\\s*" +
                                                                            "matchId:\\d*,\\s*" +
                                                                            "matchCentreData: (\\{.*}),\\s*" +
                                                                            "matchCentreEventTypeJson:.*",
                                                                            Pattern.DOTALL);

    /**
     * Player id <-> name dictionary.
     */
    private PlayerIdNameDictionary playerIdNameDictionary;
    /**
     * Match elapsed time string.
     */
    private String elapsed;
    /**
     * Home team data.
     */
    private Team home;
    /**
     * Away team data.
     */
    private Team away;

    /**
     * Elapsed time can sometime have weird values like MT, MS, IY, FIN etc.
     * This method maps those values to non weird values like HT, FT.
     *
     * @return Sanitized elapsed match time string.
     */
    public String getSanitizedElapsed() {
        // Currently not using this but if it turns out we need to it's here.
        switch(this.elapsed) {
            case "MT":
            case "IY":
                return "HT";
            case "FIN":
            case "MS":
                return "FT";
            default:
                return this.elapsed.replace("+", "");
        }
    }

}
