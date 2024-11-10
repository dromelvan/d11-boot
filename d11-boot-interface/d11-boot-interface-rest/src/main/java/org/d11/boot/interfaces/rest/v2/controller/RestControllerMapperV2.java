package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.UpdatePlayerSeasonStatInputDTO;
import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.UpdatePlayerSeasonStatInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Mapper for V2 REST controllers.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
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

}
