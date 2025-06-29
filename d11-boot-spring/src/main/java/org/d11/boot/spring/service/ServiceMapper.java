package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.model.TransferWindowInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Mapper for mapping in services.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {

    /**
     * Maps a player input to a new player.
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

    /**
     * Maps a transfer day input to an existing transfer day.
     *
     * @param transferDayInput The transfer day input.
     * @param transferDay The transfer day.
     * @return Updated transfer day.
     */
    TransferDay mapToTransferDay(TransferDayInput transferDayInput, @MappingTarget TransferDay transferDay);

    /**
     * Maps a transfer window input to an existing transfer window.
     *
     * @param transferWindowInput The transfer window input.
     * @param transferWindow The transfer window.
     * @return Updated transfer window.
     */
    TransferWindow mapToTransferWindow(TransferWindowInput transferWindowInput,
                                       @MappingTarget TransferWindow transferWindow);

    /**
     * Maps a transfer input to a new transfer.
     *
     * @param transferInput The transfer input.
     * @return Mapped transfer.
     */
    Transfer mapToTransfer(TransferInput transferInput);

}
