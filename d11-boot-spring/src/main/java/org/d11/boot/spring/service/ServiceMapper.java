package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Mapper for mapping in services.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {

    /**
     * Maps a player input to an new player.
     *
     * @param playerInput The player input.
     * @return Mapped player.
     */
    Player mapToPlayer(PlayerInput playerInput);

    /**
     * Maps a player input to an existing player.
     *
     * @param playerInput The player input.
     * @param player      The player.
     * @return Updated player.
     */
    Player mapToPlayer(PlayerInput playerInput, @MappingTarget Player player);

}
