package org.d11.boot.parser;

import org.d11.boot.parser.match.MatchParser;
import org.d11.boot.parser.match.whoscored.v1.WhoScoredMatchParserV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures file parser properties.
 */
@Configuration
public class ParserConfiguration {

    /**
     * Provides the match parser implementation used to parse match pages.
     *
     * @return The match parser implementation used to parse match pages.
     */
    @Bean
    public MatchParser matchParser() {
        return new WhoScoredMatchParserV1();
    }

}
