package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferBidApi;
import org.d11.boot.api.v2.model.CreateTransferBidRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferBidDTO;
import org.d11.boot.api.v2.model.TransferBidResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferBidsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.security.RoleUser;
import org.d11.boot.spring.service.TransferBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer bid API REST controller implementation.
 */
@RestController
public class TransferBidControllerV2 extends RepositoryServiceController<TransferBidService> implements TransferBidApi {

    /**
     * Create a new controller.
     *
     * @param transferBidService The service the controller will use.
     */
    @Autowired
    public TransferBidControllerV2(final TransferBidService transferBidService) {
        super(transferBidService);
    }

    @Override
    public ResponseEntity<TransferBidsResponseBodyDTO> getTransferBidsByTransferDayId(final Long transferDayId) {
        final List<TransferBid> transferBids = getRepositoryService().getByTransferDayId(transferDayId);
        return ResponseEntity.ok(new TransferBidsResponseBodyDTO()
                .transferBids(map(transferBids, TransferBidDTO.class)));
    }

    @Override
    @RoleUser
    public ResponseEntity<TransferBidResponseBodyDTO> createTransferBid(
            final CreateTransferBidRequestBodyDTO createTransferBidRequestBodyDTO) {
        final TransferBid transferBid = getRepositoryService()
                .createTransferBid(createTransferBidRequestBodyDTO.getTransferBid().getPlayerId(),
                                   createTransferBidRequestBodyDTO.getTransferBid().getFee());
        return ResponseEntity.ok(new TransferBidResponseBodyDTO().transferBid(map(transferBid, TransferBidDTO.class)));
    }

    @Override
    @RoleUser
    public ResponseEntity<Void> deleteTransferBid(final Long transferBidId) {
        getRepositoryService().deleteTransferBid(transferBidId);
        return ResponseEntity.noContent().build();
    }

}
