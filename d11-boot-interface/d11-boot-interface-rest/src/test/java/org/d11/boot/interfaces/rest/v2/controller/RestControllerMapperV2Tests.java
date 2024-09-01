package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Team;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Rest controller mapper tests.
 */
class RestControllerMapperV2Tests extends EasyRandomTests {

    /**
     * Rest controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

    /**
     * Tests RestControllerMapper::mapToPlayerInput.
     */
    @Test
    void testMapToPlayerInput() {
        final PlayerInputDTO source = generate(PlayerInputDTO.class);

        final PlayerInput destination = this.mapper.mapToPlayerInput(source);

        assertEquals(source.getWhoscoredId(), destination.whoscoredId(),
                     "RestControllerMapper::mapToPlayerInput destination whoscoredId equals");
        assertEquals(source.getPremierLeagueId(), destination.premierLeagueId(),
                     "RestControllerMapper::mapToPlayerInput destination premierLeagueId equals");
        assertEquals(source.getFirstName(), destination.firstName(),
                     "RestControllerMapper::mapToPlayerInput destination firstName equals");
        assertEquals(source.getLastName(), destination.lastName(),
                     "RestControllerMapper::mapToPlayerInput destination lastName equals");
        assertEquals(source.getFullName(), destination.fullName(),
                     "RestControllerMapper::mapToPlayerInput destination fullName equals");
        assertEquals(source.getDateOfBirth(), destination.dateOfBirth(),
                     "RestControllerMapper::mapToPlayerInput destination dateOfBirth equals");
        assertEquals(source.getHeight(), destination.height(),
                     "RestControllerMapper::mapToPlayerInput destination height equals");
        assertEquals(source.getCountry().getId(), destination.countryId(),
                     "RestControllerMapper::mapToPlayerInput destination countryId equals");
        assertEquals(source.isVerified(), destination.verified(),
                     "RestControllerMapper::mapToPlayerInput destination verified equals");
    }

    /**
     * Tests RestControllerMapper::mapToPlayerDTO.
     */
    @Test
    void testMapToPlayerDTO() {
        final Player source = generate(Player.class);

        final PlayerDTO destination = this.mapper.mapToPlayerDTO(source);

        assertEquals(source.getWhoscoredId(), destination.getWhoscoredId(),
                     "RestControllerMapper::mapToPlayerDTO destination whoscoredId equals");
        assertEquals(source.getPremierLeagueId(), destination.getPremierLeagueId(),
                     "RestControllerMapper::mapToPlayerDTO destination premierLeagueId equals");
        assertEquals(source.getFirstName(), destination.getFirstName(),
                     "RestControllerMapper::mapToPlayerDTO destination firstName equals");
        assertEquals(source.getLastName(), destination.getLastName(),
                     "RestControllerMapper::mapToPlayerDTO destination lastName equals");
        assertEquals(source.getFullName(), destination.getFullName(),
                     "RestControllerMapper::mapToPlayerDTO destination fullName equals");
        assertEquals(source.getDateOfBirth(), destination.getDateOfBirth(),
                     "RestControllerMapper::mapToPlayerDTO destination dateOfBirth equals");
        assertEquals(source.getHeight(), destination.getHeight(),
                     "RestControllerMapper::mapToPlayerDTO destination height equals");
        assertEquals(source.getCountry().getId(), destination.getCountry().getId(),
                     "RestControllerMapper::mapToPlayerDTO destination country id equals");
        assertEquals(source.isVerified(), destination.isVerified(),
                     "RestControllerMapper::mapToPlayerDTO destination verified equals");
    }

    /**
     * Tests RestControllerMapper::mapToPlayerSearchResultDTO.
     */
    @Test
    void testMapToPlayerSearchResultDTO() {
        final Player player = generate(Player.class);
        final Team team = generate(Team.class);

        @SuppressWarnings({ "PMD.CommentRequired", "checkstyle:AnonInnerLength" })
        final PlayerSearchResult source = new PlayerSearchResult() {
            @Override
            public Long getId() {
                return player.getId();
            }

            @Override
            public String getName() {
                return player.getName();
            }

            @Override
            public Long getTeamId() {
                return team.getId();
            }

            @Override
            public String getTeamName() {
                return team.getName();
            }
        };

        final PlayerSearchResultDTO destination = this.mapper.mapToPlayerSearchResultDTO(source);

        assertEquals(source.getId(), destination.getId(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination id equals");
        assertEquals(source.getName(), destination.getName(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination name equals");
        assertEquals(source.getTeamId(), destination.getTeamId(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination team id equals");
        assertEquals(source.getTeamName(), destination.getTeamName(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination team name equals");
    }

    /**
     * Tests RestControllerMapper::mapToCreatePlayerSeasonStatInput.
     */
    @Test
    void testMapToCreatePlayerSeasonStatInput() {
        final CreatePlayerSeasonStatInputDTO source = generate(CreatePlayerSeasonStatInputDTO.class);

        final CreatePlayerSeasonStatInput destination = this.mapper.mapToCreatePlayerSeasonStatInput(source);

        assertEquals(source.getPlayerId(), destination.playerId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player id equals");
        assertEquals(source.getTeamId(), destination.teamId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team id equals");
        assertEquals(source.getPositionId(), destination.positionId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position id equals");
    }

    /**
     * Tests RestControllerMapper::mapToPlayerSeasonStatDTO.
     */
    @Test
    @SuppressWarnings({ "checkstyle:LineLength",
                        "checkstyle:ExecutableStatementCount",
                        "checkstyle:JavaNCSS",
                        "PMD.ExcessiveMethodLength",
                        "PMD.NcssCount" })
    void testMapToPlayerSeasonStatDTO() {
        final PlayerSeasonStat source = generate(PlayerSeasonStat.class);
        source.setFormMatchPoints(Arrays.asList(1, 2, 3));

        final PlayerSeasonStatDTO destination = this.mapper.mapToPlayerSeasonStatDTO(source);

        assertEquals(source.getId(), destination.getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination id equals");

        assertEquals(source.getShirtNumber(), destination.getShirtNumber(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination shirtNumber equals");
        assertEquals(source.getFee(), destination.getFee(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination fee equals");
        assertEquals(source.getWinCount(), destination.getWinCount(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination winCount equals");
        assertEquals(source.getRanking(), destination.getRanking(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination ranking equals");
        assertEquals(source.getPoints(), destination.getPoints(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination points equals");
        assertEquals(source.getFormPoints(), destination.getFormPoints(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination formPoints equals");
        assertEquals(source.getFormMatchPoints(), destination.getFormMatchPoints(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination formMatchPoints equals");
        assertEquals(source.getPointsPerAppearance(), destination.getPointsPerAppearance(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination pointsPerAppearance equals");
        assertEquals(source.getGoals(), destination.getGoals(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination goals equals");
        assertEquals(source.getGoalAssists(), destination.getGoalAssists(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination goalAssists equals");
        assertEquals(source.getOwnGoals(), destination.getOwnGoals(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination ownGoals equals");
        assertEquals(source.getGoalsConceded(), destination.getGoalsConceded(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination goalsConceded equals");
        assertEquals(source.getCleanSheets(), destination.getCleanSheets(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination cleanSheets equals");
        assertEquals(source.getYellowCards(), destination.getYellowCards(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination yellowCards equals");
        assertEquals(source.getRedCards(), destination.getRedCards(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination redCards equals");
        assertEquals(source.getSubstitutionsOn(), destination.getSubstitutionsOn(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination substitutionsOn equals");
        assertEquals(source.getSubstitutionsOff(), destination.getSubstitutionsOff(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination substitutionsOff equals");
        assertEquals(source.getManOfTheMatch(), destination.getManOfTheMatch(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination manOfTheMatch equals");
        assertEquals(source.getSharedManOfTheMatch(), destination.getSharedManOfTheMatch(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination sharedManOfTheMatch equals");
        assertEquals(source.getRating(), destination.getRating(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination rating equals");
        assertEquals(source.getGamesStarted(), destination.getGamesStarted(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination gamesStarted equals");
        assertEquals(source.getGamesSubstitute(), destination.getGamesSubstitute(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination gamesSubstitute equals");
        assertEquals(source.getGamesDidNotParticipate(), destination.getGamesDidNotParticipate(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination gamesDidNotParticipate equals");
        assertEquals(source.getMinutesPlayed(), destination.getMinutesPlayed(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination minutesPlayed equals");

        assertEquals(source.getPlayer().getId(), destination.getPlayer().getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player id equals");
        assertEquals(source.getPlayer().getFirstName(), destination.getPlayer().getFirstName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player firstName equals");
        assertEquals(source.getPlayer().getLastName(), destination.getPlayer().getLastName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player lastName equals");
        assertEquals(source.getPlayer().getName(), destination.getPlayer().getName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player name equals");
        assertEquals(source.getPlayer().getShortName(), destination.getPlayer().getShortName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player shortName equals");
        assertEquals(source.getPlayer().getParameterizedName(), destination.getPlayer().getParameterizedName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player parameterizedName equals");
        assertEquals(source.getPlayer().getPhotoFileName(), destination.getPlayer().getPhotoFileName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination player photoFileName equals");

        assertEquals(source.getSeason().getId(), destination.getSeason().getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season id equals");
        assertEquals(source.getSeason().getName(), destination.getSeason().getName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season name equals");
        assertEquals(source.getSeason().getShortName(), destination.getSeason().getShortName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season shortName equals");
        assertEquals(source.getSeason().getD11TeamBudget(), destination.getSeason().getD11TeamBudget(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season d11TeamBudget equals");
        assertEquals(source.getSeason().getD11TeamMaxTransfers(), destination.getSeason().getD11TeamMaxTransfers(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season d11TeamMaxTransfers equals");
        assertEquals(source.getSeason().getStatus().getName(), destination.getSeason().getStatus().getValue(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season status equals");
        assertEquals(source.getSeason().getDate(), destination.getSeason().getDate(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season date equals");
        assertEquals(source.getSeason().isLegacy(), destination.getSeason().isLegacy(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination season legacy equals");

        assertEquals(source.getTeam().getId(), destination.getTeam().getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team id equals");
        assertEquals(source.getTeam().getName(), destination.getTeam().getName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team name equals");
        assertEquals(source.getTeam().getShortName(), destination.getTeam().getShortName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team shortName equals");
        assertEquals(source.getTeam().getCode(), destination.getTeam().getCode(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team code equals");
        assertEquals(source.getTeam().isDummy(), destination.getTeam().isDummy(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team dummy equals");
        assertEquals(source.getTeam().getPhotoFileName(), destination.getTeam().getPhotoFileName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination team photoFileName equals");

        assertEquals(source.getD11Team().getId(), destination.getD11Team().getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team id equals");
        assertEquals(source.getD11Team().getName(), destination.getD11Team().getName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team name equals");
        assertEquals(source.getD11Team().getShortName(), destination.getD11Team().getShortName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team shortName equals");
        assertEquals(source.getD11Team().getCode(), destination.getD11Team().getCode(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team code equals");
        assertEquals(source.getD11Team().isDummy(), destination.getD11Team().isDummy(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team dummy equals");
        assertEquals(source.getD11Team().getPhotoFileName(), destination.getD11Team().getPhotoFileName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination D11 team photoFileName equals");

        assertEquals(source.getPosition().getId(), destination.getPosition().getId(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position id equals");
        assertEquals(source.getPosition().getName(), destination.getPosition().getName(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position name equals");
        assertEquals(source.getPosition().getCode(), destination.getPosition().getCode(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position code equals");
        assertEquals(source.getPosition().getMaxCount(), destination.getPosition().getMaxCount(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position maxCount equals");
        assertEquals(source.getPosition().isDefender(), destination.getPosition().isDefender(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position defender equals");
        assertEquals(source.getPosition().getSortOrder(), destination.getPosition().getSortOrder(),
                     "RestControllerMapper::mapToCreatePlayerSeasonStatInput destination position sortOrder equals");
    }

}
