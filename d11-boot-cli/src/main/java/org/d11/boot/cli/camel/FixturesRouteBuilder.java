package org.d11.boot.cli.camel;

import org.d11.boot.camel.AbstractRouteBuilder;
import org.d11.boot.cli.camel.processor.FixturesProcessor;
import org.d11.boot.parser.fixtures.FixturesParser;
import org.springframework.stereotype.Component;

/**
 * Builds a route to parse a list of WhoScored fixture files and save the SQL output to a new file.
 * The WhoScored files have to be downloaded by going to their fixtures page, inspecting, copying the top html element
 * and saving to a file.
 */
@Component
public class FixturesRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        from("{{app.route.fixtures}}")
                .routeId("PARSE_FIXTURES")
                // Body is list of File
                .split(simple("${body}"), new FixturesAggregationStrategy())
                    // Body is File
                    .bean(FixturesParser.class, "parse(${body})")
                .end()
                // Body is list of MatchData parsed from all files
                .process(new FixturesProcessor()).
                // Body is SQL string. Write it to a file.
                to("file:files/data/whoscored.com/fixtures/?fileName=fixtures.sql");
    }

}
