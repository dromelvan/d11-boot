package org.d11.boot.cli.command;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.AdministrationRequestResultDTO;
import org.d11.boot.client.api.AdministrationApi;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

/**
 * Command for updating D11 squads according to player data from PremierLeague.com.
 */
@Slf4j
@Component
@Command(name = "squads", mixinStandardHelpOptions = true)
public class SquadsCommand extends AbstractFeignCommand {

    @Override
    protected void call(final AdministrationApi administrationApi) {
        final AdministrationRequestResultDTO administrationRequestResultDTO = administrationApi.updateSquads();
        log.info(administrationRequestResultDTO.getMessage());
    }

}
