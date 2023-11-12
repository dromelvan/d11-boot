package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transfer day service.
 */
@Service
public class TransferDayService extends RepositoryService<TransferDay, TransferDayRepository> {

    /**
     * Creates a new transfer day service.
     *
     * @param transferDayRepository The repository the service will use.
     */
    @Autowired
    public TransferDayService(final TransferDayRepository transferDayRepository) {
        super(TransferDay.class, transferDayRepository);
    }

}
