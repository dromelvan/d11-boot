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
import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferDayStatusInput;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.spring.model.UpdatePlayerSeasonStatInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Mapper for V2 REST controllers.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@SuppressWarnings({ "PMD.TooManyMethods", "checkstyle.ClassFanOutComplexity" })
public interface RestControllerMapperV2 {

    /**
     * Maps a PlayerInputDTO to a PlayerInput.
     *
     * @param playerInputDTO The PlayerInputDTO.
     * @return Mapped PlayerInput.
     */
    @Mapping(source = "country.id", target = "countryId")
    PlayerInput mapToPlayerInput(PlayerInputDTO playerInputDTO);

    /**
     * Maps a Player to a PlayerDTO.
     *
     * @param player The Player.
     * @return Mapped PlayerDTO.
     */
    PlayerDTO mapToPlayerDTO(Player player);

    /**
     * Maps a PlayerSearchResult to a PlayerSearchResultDTO.
     *
     * @param playerSearchResult The PlayerSearchResult.
     * @return Mapped PlayerSearchResultDTO.
     */
    PlayerSearchResultDTO mapToPlayerSearchResultDTO(PlayerSearchResult playerSearchResult);

    /**
     * Maps a CreatePlayerSeasonStatInputDTO to a CreatePlayerSeasonStatInput.
     *
     * @param createPlayerSeasonStatInputDTO The CreatePlayerSeasonStatInputDTO.
     * @return Mapped CreatePlayerSeasonStatInput.
     */
    CreatePlayerSeasonStatInput mapToCreatePlayerSeasonStatInput(
            CreatePlayerSeasonStatInputDTO createPlayerSeasonStatInputDTO);

    /**
     * Maps an UpdatePlayerSeasonStatInputDTO to an UpdatePlayerSeasonStatInput.
     *
     * @param updatePlayerSeasonStatInputDTO The UpdatePlayerSeasonStatInputDTO.
     * @return Mapped UpdatePlayerSeasonStatInput.
     */
    UpdatePlayerSeasonStatInput mapToUpdatePlayerSeasonStatInput(
            UpdatePlayerSeasonStatInputDTO updatePlayerSeasonStatInputDTO);

    /**
     * Maps a PlayerSeasonStat to a PlayerSeasonStatDTO.
     *
     * @param playerSeasonStat The PlayerSeasonStatDTO.
     * @return Mapped PlayerSeasonStat.
     */
    PlayerSeasonStatDTO mapToPlayerSeasonStatDTO(PlayerSeasonStat playerSeasonStat);

    /**
     * Maps a TransferWindowInputDTO to a TransferWindowInput.
     *
     * @param transferWindowInputDTO The TransferWindowInputDTO.
     * @return Mapped TransferWindowInput.
     */
    TransferWindowInput mapToTransferWindowInput(TransferWindowInputDTO transferWindowInputDTO);

    /**
     * Maps a TransferDayInputDTO to a TransferDayInput.
     *
     * @param transferDayInputDTO The TransferDayInputDTO.
     * @return Mapped TransferDayInput.
     */
    TransferDayInput mapToTransferDayInput(TransferDayInputDTO transferDayInputDTO);

    /**
     * Maps a TransferDayStatusInputDTO to a TransferDayStatusInput.
     *
     * @param transferDayStatusInputDTO The TransferDayStatusInputDTO.
     * @return Mapped TransferDayStatusInput.
     */
    TransferDayStatusInput mapToTransferDayStatusInput(TransferDayStatusInputDTO transferDayStatusInputDTO);

    /**
     * Maps a TransferInputDTO to a TransferInput.
     *
     * @param transferInputDTO The TransferInputDTO.
     * @return Mapped TransferInput.
     */
    TransferInput mapToTransferInput(TransferInputDTO transferInputDTO);

    /**
     * Maps a Transfer to a TransferDTO.
     *
     * @param transfer The Transfer.
     * @return Mapped TransferDTO.
     */
    TransferDTO mapToTransferDTO(Transfer transfer);

    /**
     * Maps a PlayerTransferContext to PlayerTransferContextDTO.
     *
     * @param playerTransferContext The PlayerTransferContext.
     * @return Mapped PlayerTransferContextDTO.
     */
    @Mapping(source = "player.id", target = "playerId")
    @Mapping(source = "deletableTransferListing.id", target = "deletableTransferListingId")
    PlayerTransferContextDTO mapToPlayerTransferContextDTO(PlayerTransferContext playerTransferContext);

}
