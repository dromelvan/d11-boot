package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerTransferContextDTO;
import org.d11.boot.api.v2.model.TransferDTO;
import org.d11.boot.api.v2.model.TransferDayInputDTO;
import org.d11.boot.api.v2.model.TransferDayStatusInputDTO;
import org.d11.boot.api.v2.model.TransferInputDTO;
import org.d11.boot.api.v2.model.TransferWindowInputDTO;
import org.d11.boot.api.v2.model.UpdatePlayerSeasonStatInputDTO;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferDayStatusInput;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.spring.model.UpdatePlayerSeasonStatInput;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Rest controller mapper tests.
 */
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
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

        assertEquals(source.getWhoscoredId(), destination.whoscoredId());
        assertEquals(source.getPremierLeagueId(), destination.premierLeagueId());
        assertEquals(source.getFirstName(), destination.firstName());
        assertEquals(source.getLastName(), destination.lastName());
        assertEquals(source.getFullName(), destination.fullName());
        assertEquals(source.getDateOfBirth(), destination.dateOfBirth());
        assertEquals(source.getHeight(), destination.height());
        assertEquals(source.getCountry().getId(), destination.countryId());
        assertEquals(source.isVerified(), destination.verified());
    }

    /**
     * Tests RestControllerMapper::mapToPlayerDTO.
     */
    @Test
    void testMapToPlayerDTO() {
        final Player source = generate(Player.class);

        final PlayerDTO destination = this.mapper.mapToPlayerDTO(source);

        assertEquals(source.getWhoscoredId(), destination.getWhoscoredId());
        assertEquals(source.getPremierLeagueId(), destination.getPremierLeagueId());
        assertEquals(source.getFirstName(), destination.getFirstName());
        assertEquals(source.getLastName(), destination.getLastName());
        assertEquals(source.getFullName(), destination.getFullName());
        assertEquals(source.getDateOfBirth(), destination.getDateOfBirth());
        assertEquals(source.getHeight(), destination.getHeight());
        assertEquals(source.getCountry().getId(), destination.getCountry().getId());
        assertEquals(source.isVerified(), destination.isVerified());
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

        assertEquals(source.getId(), destination.getId());
        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getTeamId(), destination.getTeamId());
        assertEquals(source.getTeamName(), destination.getTeamName());
    }

    /**
     * Tests RestControllerMapper::mapToCreatePlayerSeasonStatInput.
     */
    @Test
    void testMapToCreatePlayerSeasonStatInput() {
        final CreatePlayerSeasonStatInputDTO source = generate(CreatePlayerSeasonStatInputDTO.class);

        final CreatePlayerSeasonStatInput destination = this.mapper.mapToCreatePlayerSeasonStatInput(source);

        assertEquals(source.getPlayerId(), destination.playerId());
        assertEquals(source.getTeamId(), destination.teamId());
        assertEquals(source.getPositionId(), destination.positionId());
    }

    /**
     * Tests RestControllerMapper::mapToUpdatePlayerSeasonStatInput.
     */
    @Test
    void testMapToUpdatePlayerSeasonStatInput() {
        final UpdatePlayerSeasonStatInputDTO source = generate(UpdatePlayerSeasonStatInputDTO.class);

        final UpdatePlayerSeasonStatInput destination = this.mapper.mapToUpdatePlayerSeasonStatInput(source);

        assertEquals(source.getTeamId(), destination.teamId());
        assertEquals(source.getD11TeamId(), destination.d11TeamId());
        assertEquals(source.getPositionId(), destination.positionId());
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

        assertEquals(source.getId(), destination.getId());

        assertEquals(source.getShirtNumber(), destination.getShirtNumber());
        assertEquals(source.getFee(), destination.getFee());
        assertEquals(source.getWinCount(), destination.getWinCount());
        assertEquals(source.getRanking(), destination.getRanking());
        assertEquals(source.getPoints(), destination.getPoints());
        assertEquals(source.getFormPoints(), destination.getFormPoints());
        assertEquals(source.getFormMatchPoints(), destination.getFormMatchPoints());
        assertEquals(source.getPointsPerAppearance(), destination.getPointsPerAppearance());
        assertEquals(source.getGoals(), destination.getGoals());
        assertEquals(source.getGoalAssists(), destination.getGoalAssists());
        assertEquals(source.getOwnGoals(), destination.getOwnGoals());
        assertEquals(source.getGoalsConceded(), destination.getGoalsConceded());
        assertEquals(source.getCleanSheets(), destination.getCleanSheets());
        assertEquals(source.getYellowCards(), destination.getYellowCards());
        assertEquals(source.getRedCards(), destination.getRedCards());
        assertEquals(source.getSubstitutionsOn(), destination.getSubstitutionsOn());
        assertEquals(source.getSubstitutionsOff(), destination.getSubstitutionsOff());
        assertEquals(source.getManOfTheMatch(), destination.getManOfTheMatch());
        assertEquals(source.getSharedManOfTheMatch(), destination.getSharedManOfTheMatch());
        assertEquals(source.getRating(), destination.getRating());
        assertEquals(source.getGamesStarted(), destination.getGamesStarted());
        assertEquals(source.getGamesSubstitute(), destination.getGamesSubstitute());
        assertEquals(source.getGamesDidNotParticipate(), destination.getGamesDidNotParticipate());
        assertEquals(source.getMinutesPlayed(), destination.getMinutesPlayed());

        assertEquals(source.getPlayer().getId(), destination.getPlayer().getId());
        assertEquals(source.getPlayer().getFirstName(), destination.getPlayer().getFirstName());
        assertEquals(source.getPlayer().getLastName(), destination.getPlayer().getLastName());
        assertEquals(source.getPlayer().getName(), destination.getPlayer().getName());
        assertEquals(source.getPlayer().getShortName(), destination.getPlayer().getShortName());
        assertEquals(source.getPlayer().getParameterizedName(), destination.getPlayer().getParameterizedName());
        assertEquals(source.getPlayer().getPhotoFileName(), destination.getPlayer().getPhotoFileName());

        assertEquals(source.getSeason().getId(), destination.getSeason().getId());
        assertEquals(source.getSeason().getName(), destination.getSeason().getName());
        assertEquals(source.getSeason().getShortName(), destination.getSeason().getShortName());
        assertEquals(source.getSeason().getD11TeamBudget(), destination.getSeason().getD11TeamBudget());
        assertEquals(source.getSeason().getD11TeamMaxTransfers(), destination.getSeason().getD11TeamMaxTransfers());
        assertEquals(source.getSeason().getStatus().getName(), destination.getSeason().getStatus().getValue());
        assertEquals(source.getSeason().getDate(), destination.getSeason().getDate());
        assertEquals(source.getSeason().isLegacy(), destination.getSeason().isLegacy());

        assertEquals(source.getTeam().getId(), destination.getTeam().getId());
        assertEquals(source.getTeam().getName(), destination.getTeam().getName());
        assertEquals(source.getTeam().getShortName(), destination.getTeam().getShortName());
        assertEquals(source.getTeam().getCode(), destination.getTeam().getCode());
        assertEquals(source.getTeam().isDummy(), destination.getTeam().isDummy());
        assertEquals(source.getTeam().getPhotoFileName(), destination.getTeam().getPhotoFileName());

        assertEquals(source.getD11Team().getId(), destination.getD11Team().getId());
        assertEquals(source.getD11Team().getName(), destination.getD11Team().getName());
        assertEquals(source.getD11Team().getShortName(), destination.getD11Team().getShortName());
        assertEquals(source.getD11Team().getCode(), destination.getD11Team().getCode());
        assertEquals(source.getD11Team().isDummy(), destination.getD11Team().isDummy());
        assertEquals(source.getD11Team().getPhotoFileName(), destination.getD11Team().getPhotoFileName());

        assertEquals(source.getPosition().getId(), destination.getPosition().getId());
        assertEquals(source.getPosition().getName(), destination.getPosition().getName());
        assertEquals(source.getPosition().getCode(), destination.getPosition().getCode());
        assertEquals(source.getPosition().getMaxCount(), destination.getPosition().getMaxCount());
        assertEquals(source.getPosition().isDefender(), destination.getPosition().isDefender());
        assertEquals(source.getPosition().getSortOrder(), destination.getPosition().getSortOrder());
    }

    /**
     * Tests RestControllerMapper::mapToTransferWindowInput.
     */
    @Test
    void testMapToTransferWindowInput() {
        final TransferWindowInputDTO source = generate(TransferWindowInputDTO.class);
        final TransferWindowInput destination = this.mapper.mapToTransferWindowInput(source);

        assertEquals(source.getTransferWindowNumber(), destination.transferWindowNumber());
        assertEquals(source.isDraft(), destination.draft());
        assertEquals(source.getStatus().name(), destination.status().name());
        assertEquals(source.getDatetime(), destination.datetime());
        assertEquals(source.getMatchWeekId(), destination.matchWeekId());
    }

    /**
     * Tests RestControllerMapper::mapToTransferDayInput.
     */
    @Test
    void testMapToTransferDayInput() {
        final TransferDayInputDTO source = generate(TransferDayInputDTO.class);
        final TransferDayInput destination = this.mapper.mapToTransferDayInput(source);

        assertEquals(source.getTransferDayNumber(), destination.transferDayNumber());
        assertEquals(source.getStatus().name(), destination.status().name());
        assertEquals(source.getDatetime(), destination.datetime());
    }

    /**
     * Tests RestControllerMapper::mapToTransferDayStatusInput.
     */
    @Test
    void testMapToTransferDayStatusInput() {
        final TransferDayStatusInputDTO source = generate(TransferDayStatusInputDTO.class);
        final TransferDayStatusInput destination = this.mapper.mapToTransferDayStatusInput(source);

        assertEquals(source.getStatus().name(), destination.status().name());
        assertEquals(source.isProcess(), destination.process());
    }

    /**
     * Tests RestControllerMapper::mapToTransferInput.
     */
    @Test
    void testMapToTransferInput() {
        final TransferInputDTO source = generate(TransferInputDTO.class);
        final TransferInput destination = this.mapper.mapToTransferInput(source);

        assertEquals(source.getFee(), destination.fee());
        assertEquals(source.getTransferDayId(), destination.transferDayId());
        assertEquals(source.getPlayerId(), destination.playerId());
        assertEquals(source.getD11TeamId(), destination.d11TeamId());
    }

    /**
     * Tests RestControllerMapper::mapToTransferDTO.
     */
    @Test
    void testMapToTransferDTO() {
        final Transfer source = generate(Transfer.class);
        final TransferDTO destination = this.mapper.mapToTransferDTO(source);

        assertEquals(source.getId(), destination.getId());
        assertEquals(source.getFee(), destination.getFee());

        assertEquals(source.getTransferDay().getId(), destination.getTransferDay().getId());
        assertEquals(source.getTransferDay().getTransferDayNumber(),
                     destination.getTransferDay().getTransferDayNumber());
        assertEquals(source.getTransferDay().getStatus().getName(),
                     destination.getTransferDay().getStatus().getValue());
        assertEquals(source.getTransferDay().getDatetime(), destination.getTransferDay().getDatetime());

        assertEquals(source.getPlayer().getId(), destination.getPlayer().getId());
        assertEquals(source.getPlayer().getFirstName(), destination.getPlayer().getFirstName());
        assertEquals(source.getPlayer().getLastName(), destination.getPlayer().getLastName());
        assertEquals(source.getPlayer().getName(), destination.getPlayer().getName());
        assertEquals(source.getPlayer().getShortName(), destination.getPlayer().getShortName());
        assertEquals(source.getPlayer().getParameterizedName(), destination.getPlayer().getParameterizedName());

        assertEquals(source.getD11Team().getId(), destination.getD11Team().getId());
        assertEquals(source.getD11Team().getName(), destination.getD11Team().getName());
        assertEquals(source.getD11Team().getShortName(), destination.getD11Team().getShortName());
        assertEquals(source.getD11Team().getCode(), destination.getD11Team().getCode());
        assertEquals(source.getD11Team().isDummy(), destination.getD11Team().isDummy());
    }

    /**
     * Tests RestControllerMapper::mapToPlayerTransferContext with transferDay status PENDING.
     */
    @Test
    void testMapToPlayerTransferContextDTOPending() {
        final PlayerTransferContext source = generate(PlayerTransferContext.class);
        source.getTransferDay().setStatus(Status.PENDING);
        source.getTransferListing().setD11Team(source.getD11Team());
        final PlayerTransferContextDTO destination = this.mapper.mapToPlayerTransferContextDTO(source);

        assertEquals(source.getPlayer().getId(), destination.getPlayerId());
        assertEquals(source.isTransferListable(), destination.isTransferListable());
        assertEquals(source.getDeletableTransferListing().getId(), destination.getDeletableTransferListingId());
        assertEquals(source.getMaxBid(), destination.getMaxBid());
        assertNull(destination.getActiveTransferBid());
    }

    /**
     * Tests RestControllerMapper::mapToPlayerTransferContext with transferDay status ACTIVE.
     */
    @Test
    void testMapToPlayerTransferContextDTOActive() {
        final PlayerTransferContext source = generate(PlayerTransferContext.class);
        source.getTransferDay().setStatus(Status.ACTIVE);
        source.getTransferBid().setD11Team(source.getD11Team());
        final PlayerTransferContextDTO destination = this.mapper.mapToPlayerTransferContextDTO(source);

        assertEquals(source.getPlayer().getId(), destination.getPlayerId());
        assertEquals(source.isTransferListable(), destination.isTransferListable());
        assertNull(destination.getDeletableTransferListingId());
        assertEquals(source.getMaxBid(), destination.getMaxBid());
        assertEquals(source.getActiveTransferBid().getId(), destination.getActiveTransferBid().getId());
        assertEquals(source.getActiveTransferBid().getId(), destination.getActiveTransferBid().getId());
        assertEquals(source.getActiveTransferBid().getFee(), destination.getActiveTransferBid().getFee());
    }

}
