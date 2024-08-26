package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.spring.model.PlayerInput;
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

}
