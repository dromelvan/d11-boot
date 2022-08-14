package org.d11.boot.cli.command;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.AdministrationRequestResultDTO;
import org.d11.boot.api.model.InsertTransferWindowDTO;
import org.d11.boot.client.api.AdministrationApi;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command for updating D11 squads according to player data from PremierLeague.com.
 */
@Slf4j
@Component
@Command(name = "transferwindow", mixinStandardHelpOptions = true)
public class TransferWindowCommand extends AbstractFeignCommand {

    /**
     * Date time formatter used to format LocalDateTimes.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The username that will be used when authenticating.
     */
    @Option(names = {"-d", "--datetime"}, description = "Transfer window transfer listing deadline.", required = true)
    private String datetime;

    /**
     * The username that will be used when authenticating.
     */
    @Option(names = {"-y", "--delay"}, description = "Transfer window transfer listing deadline.")
    private int transferDayDelay;

    @Override
    protected void call(final AdministrationApi administrationApi) {
        try {
            final InsertTransferWindowDTO insertTransferWindowDTO = new InsertTransferWindowDTO()
                    .datetime(LocalDateTime.parse(this.datetime, DATE_TIME_FORMATTER))
                    .transferDayDelay(this.transferDayDelay);

            final AdministrationRequestResultDTO administrationRequestResultDTO =
                    administrationApi.insertTransferWindow(insertTransferWindowDTO);
            log.info(administrationRequestResultDTO.getMessage());
        } catch(final DateTimeParseException e) {
            log.error("Invalid datetime: {}.", this.datetime);
        }
    }

}
