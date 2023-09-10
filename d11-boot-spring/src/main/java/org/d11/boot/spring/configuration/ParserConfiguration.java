package org.d11.boot.spring.configuration;

import org.d11.boot.parser.fixtures.FixturesParser;
import org.d11.boot.parser.fixtures.whoscored.v1.WhoScoredFixturesParserV1;
import org.d11.boot.parser.match.MatchParser;
import org.d11.boot.parser.match.whoscored.v1.WhoScoredMatchParserV1;
import org.d11.boot.parser.player.PlayerParser;
import org.d11.boot.parser.player.premierleague.v2.PremierLeaguePlayerParserV2;
import org.d11.boot.parser.team.TeamParser;
import org.d11.boot.parser.team.TeamsParser;
import org.d11.boot.parser.team.premierleague.v2.PremierLeagueClubsParserV2;
import org.d11.boot.parser.team.premierleague.v2.PremierLeagueSquadParserV2;
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

    /**
     * Provides the fixtures parser implementation used to parse fixtures pages.
     *
     * @return The fixtures parser implementation used to parse fixtures pages.
     */
    @Bean
    public FixturesParser fixturesParser() {
        return new WhoScoredFixturesParserV1();
    }

    /**
     * Provides the teams parser implementation used to parse teams pages.
     *
     * @return The teams parser implementation used to parse teams pages.
     */
    @Bean
    public TeamsParser teamsParser() {
        return new PremierLeagueClubsParserV2();
    }

    /**
     * Provides the team parser implementation used to parse teams pages.
     *
     * @return The team parser implementation used to parse teams pages.
     */
    @Bean
    public TeamParser teamParser() {
        return new PremierLeagueSquadParserV2();
    }

    /**
     * Provides the player parser implementation used to parse player pages.
     *
     * @return The player parser implementation used to parse player pages.
     */
    @Bean
    public PlayerParser playerParser() {
        return new PremierLeaguePlayerParserV2();
    }

}
