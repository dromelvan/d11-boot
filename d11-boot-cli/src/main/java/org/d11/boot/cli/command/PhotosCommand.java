package org.d11.boot.cli.command;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.AdministrationRequestResultDTO;
import org.d11.boot.client.api.AdministrationApi;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

/**
 * Command for downloading player photos from PremierLeague.com.
 */
@Slf4j
@Component
@Command(name = "photos", mixinStandardHelpOptions = true)
public class PhotosCommand extends AbstractFeignCommand {

    @Override
    protected void call(final AdministrationApi administrationApi) {
        final AdministrationRequestResultDTO administrationRequestResultDTO = administrationApi.updatePhotos();
        log.info(administrationRequestResultDTO.getMessage());
    }

}
