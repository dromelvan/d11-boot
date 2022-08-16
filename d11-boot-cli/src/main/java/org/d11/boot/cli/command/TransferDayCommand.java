package org.d11.boot.cli.command;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.StatusDTO;
import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayResultDTO;
import org.d11.boot.client.api.AdministrationApi;
import org.d11.boot.client.api.TransferDayApi;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Command for updating status of transfer days.
 */
@Slf4j
@Component
@Command(name = "transferday", mixinStandardHelpOptions = true)
public class TransferDayCommand extends AbstractFeignCommand {

    /**
     * The status the transfer day will be changed to.
     */
    @Option(names = {"-s", "--status"}, description = "The status the transfer day will be changed to.",
            required = true)
    private StatusDTO status;

    @Override
    protected void preCall() {
        log.info("Updating transfer day status.");
    }

    @Override
    protected void call(final AdministrationApi administrationApi) {
        final TransferDayApi transferDayApi = getClient(TransferDayApi.class);
        final TransferDayDTO transferDayDTO = transferDayApi.findCurrentTransferDay();
        final UpdateTransferDayDTO updateTransferDayDTO = new UpdateTransferDayDTO()
                .id(transferDayDTO.getId());

        if(StatusDTO.PENDING.equals(transferDayDTO.getStatus())
            && StatusDTO.ACTIVE.equals(this.status)) {
            updateTransferDayDTO.setStatus(this.status);
        } else if(StatusDTO.ACTIVE.equals(transferDayDTO.getStatus())
                   && StatusDTO.FINISHED.equals(this.status)) {
            updateTransferDayDTO.setStatus(this.status);
        } else {
            log.error(String.format("Transfer day with status %s cannot be changed to %s.", transferDayDTO.getStatus(), this.status));
            return;
        }
        final UpdateTransferDayResultDTO updateTransferDayResultDTO = transferDayApi.updateTransferDay(updateTransferDayDTO);

        updateTransferDayResultDTO.getErrors().forEach(log::error);
    }

    @Override
    protected void postCall() {
        log.info("Transfer day status update finished.");
    }

}
