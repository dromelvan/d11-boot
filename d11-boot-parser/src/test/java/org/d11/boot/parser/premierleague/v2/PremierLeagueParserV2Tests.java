package org.d11.boot.parser.premierleague.v2;

import org.d11.boot.jms.model.PlayerData;
import org.d11.boot.jms.model.TeamData;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.player.premierleague.v2.PremierLeaguePlayerParserV2;
import org.d11.boot.parser.team.premierleague.v2.PremierLeagueClubsParserV2;
import org.d11.boot.parser.team.premierleague.v2.PremierLeagueSquadParserV2;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Premier League V2 page parsers.
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class PremierLeagueParserV2Tests {

    /**
     * Tests parsing a Premier League V2 clubs file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parseClubs() throws IOException, ParserException {
        final PremierLeagueClubsParserV2 premierLeagueClubsParserV2 = new PremierLeagueClubsParserV2();
        final File clubsFile = new File("src/test/resources/premierleague/v2/clubs/clubs.html");
        final List<TeamData> teamDatas = premierLeagueClubsParserV2.parse(clubsFile);

        assertEquals(20, teamDatas.size(), "Result should contain 20 teams.");

        for(final TeamData teamData : teamDatas) {
            assertNotNull(teamData.getId(), "Premier League team id should not be null.");
            assertNotNull(teamData.getName(), "Premier League team name should not be null.");
            assertFalse(teamData.getName().trim().isEmpty(), "Premier League team name should not be empty.");
        }
    }

    /**
     * Tests parsing a Premier League squad file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parseSquad() throws IOException, ParserException {
        final PremierLeagueSquadParserV2 premierLeagueSquadParserV2 = new PremierLeagueSquadParserV2();
        final File squadFile = new File("src/test/resources/premierleague/v2/squad/squad.html");
        final TeamData teamData = premierLeagueSquadParserV2.parse(squadFile);

        assertNotNull(teamData, "Premier League team squad should not be null.");
        assertNotNull(teamData.getId(), "Premier League team squad team id should not be null.");
        assertNotNull(teamData.getName(), "Premier League team squad team name should not be null.");
        assertFalse(teamData.getName().trim().isEmpty(), "Premier League team squad team name should not be empty.");

        assertNotNull(teamData.getPlayers(), "Premier League team squad players should not be null.");
        assertEquals(36, teamData.getPlayers().size(), "Premier League team squad should contain 36 players.");

        for(final PlayerData playerData : teamData.getPlayers()) {
            assertNotNull(playerData.getId(), "Premier League team player id should not be null.");
            assertNotNull(playerData.getName(), "Premier League team player name should not be null.");
            assertFalse(playerData.getName().trim().isEmpty(), "Premier League team player name should not be empty.");
            assertNotNull(playerData.getShirtNumber(), "Premier League team player number should not be null.");
            assertNotNull(playerData.getPosition(), "Premier League team player position should not be null.");
            assertFalse(playerData.getPosition().trim().isEmpty(), "Premier League team player position should not be empty.");
            assertNotNull(playerData.getNationality(), "Premier League team player nationality should not be null.");
            assertFalse(playerData.getNationality().trim().isEmpty(), "Premier League team player nationality should not be empty.");
        }
    }

    /**
     * Tests parsing a Premier League player file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parsePlayer() throws IOException, ParserException {
        final PremierLeaguePlayerParserV2 premierLeaguePlayerParserV2 = new PremierLeaguePlayerParserV2();
        final File playerFile = new File("src/test/resources/premierleague/v2/player/player.html");
        final PlayerData playerData = premierLeaguePlayerParserV2.parse(playerFile);

        assertNotNull(playerData, "Premier League player should not be null");
        assertNotNull(playerData.getId(), "Premier League player id should not be null.");
        assertNotNull(playerData.getName(), "Premier League player name should not be null.");
        assertFalse(playerData.getName().trim().isEmpty(), "Premier League player name should not be empty.");
        assertNotNull(playerData.getShirtNumber(), "Premier League player number should not be null.");
        assertNotNull(playerData.getPosition(), "Premier League player position should not be null.");
        assertFalse(playerData.getPosition().trim().isEmpty(), "Premier League player position should not be empty.");
        assertNotNull(playerData.getNationality(), "Premier League player nationality should not be null.");
        assertFalse(playerData.getNationality().trim().isEmpty(), "Premier League player nationality should not be empty.");
        assertNotNull(playerData.getNationality(), "Premier League player nationality should not be null.");
        assertNotNull(playerData.getDateOfBirth(), "Premier League player date of birth should not be null.");
        assertNotNull(playerData.getHeight(), "Premier League player height should not be null.");
        assertTrue(playerData.getHeight() > 0, "Premier League player height should be greater than 0.");
    }

}
