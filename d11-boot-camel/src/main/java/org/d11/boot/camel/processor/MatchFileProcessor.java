package org.d11.boot.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes a match file by setting matchWeekDirectory, derived from the input file name, a dummy match id and finish
 * properties in the Camel exchange.
 */
public class MatchFileProcessor implements Processor {

    /**
     * Pattern for matching file names in match week directories.
     */
    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("(.*)/download/.*/(\\d{4}-\\d{4})/(\\d{2})/.*\\.htm.*");

    /**
     * Group number for the parent directory of the file.
     */
    private static final int DIRECTORY_GROUP = 1;
    /**
     * Group number for the season name.
     */
    private static final int SEASON_GROUP = 2;
    /**
     * Group number for the match week number.
     */
    private static final int MATCH_WEEK_GROUP = 3;

    @Override
    public void process(final Exchange exchange) {
        final File file = exchange.getIn().getBody(File.class);
        String matchWeekDirectory = "files/unknown";

        final Matcher matcher = FILE_NAME_PATTERN.matcher(file.getAbsolutePath());
        if(matcher.matches()) {
            matchWeekDirectory = String.format("%s/data/match/%s/%02d",
                                               matcher.group(DIRECTORY_GROUP),
                                               matcher.group(SEASON_GROUP),
                                               Integer.parseInt(matcher.group(MATCH_WEEK_GROUP)));
        }

        exchange.setProperty("matchWeekDirectory", matchWeekDirectory);
        exchange.setProperty("matchId", 0);
        exchange.setProperty("finish", false);
    }

}
