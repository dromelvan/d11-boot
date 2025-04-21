package org.d11.boot.interfaces.rest.v2.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.d11.boot.api.v2.PlayerTransferContextApi;
import org.d11.boot.api.v2.model.PlayerTransferContextResponseBodyDTO;
import org.d11.boot.interfaces.rest.D11BootRestController;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.service.PlayerTransferContextService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transfer listing API REST controller implementation.
 */
@RestController
@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
                    justification = "Service")
public class PlayerTransferContextControllerV2 extends D11BootRestController implements PlayerTransferContextApi {

    /**
     * REST controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

    /**
     * The service the controller will use.
     */
    private final PlayerTransferContextService playerTransferContextService;

    /**
     * Create a new controller.
     *
     * @param playerTransferContextService The service the controller will use.
     */
    @Autowired
    public PlayerTransferContextControllerV2(final PlayerTransferContextService playerTransferContextService) {
        this.playerTransferContextService = playerTransferContextService;
    }


    @Override
    public ResponseEntity<PlayerTransferContextResponseBodyDTO> getPlayerTransferContextByPlayerId(
            final Long playerId) {
        final PlayerTransferContext playerTransferContext = this.playerTransferContextService.getByPlayerId(playerId);

        return ResponseEntity.ok(
                new PlayerTransferContextResponseBodyDTO()
                        .playerTransferContext(this.mapper.mapToPlayerTransferContextDTO(playerTransferContext)));
    }

}
