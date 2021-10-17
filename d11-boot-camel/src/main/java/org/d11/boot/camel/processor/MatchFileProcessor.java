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

    private final static Pattern FILE_NAME_PATTERN = Pattern.compile("(.*)/download/.*/(\\d{4}-\\d{4})/(\\d{2})/.*\\.htm.*");

    @Override
    public void process(Exchange exchange) {
        final File file = exchange.getIn().getBody(File.class);
        final Matcher matcher = FILE_NAME_PATTERN.matcher(file.getAbsolutePath());
        if(matcher.matches()) {
            final String matchWeekDirectory = String.format("%s/data/match/%s/%02d", matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3)));
            exchange.setProperty("matchWeekDirectory", matchWeekDirectory);
        } else {
            exchange.setProperty("matchWeekDirectory", "files/unknown");
        }
        exchange.setProperty("matchId", 0);
        exchange.setProperty("finish", false);
    }

}
