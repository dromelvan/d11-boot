package org.d11.boot.cli.command;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.AdministrationRequestResultDTO;
import org.d11.boot.api.model.UploadMatchStatsDTO;
import org.d11.boot.client.api.AdministrationApi;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Command for uploading a json file with match stats.
 */
@Slf4j
@Component
@Command(name = "upload", mixinStandardHelpOptions = true)
public class UploadCommand extends AbstractFeignCommand {

    @Override
    protected void call(final AdministrationApi administrationApi) {
        final File[] files = getFiles(new JsonFileFilter());
        for(final File file : files) {
            try {
                log.info("Uploading file {}.", file.getName());
                final String data = FileCopyUtils.copyToString(Files.newBufferedReader(file.toPath()));
                final AdministrationRequestResultDTO administrationRequestResultDTO =
                        administrationApi.uploadMatchStats(new UploadMatchStatsDTO().data(data));
                log.info(administrationRequestResultDTO.getMessage());
            } catch(IOException e) {
                // This should never happen.
                log.error("File error.", e);
            }
        }
    }

}
