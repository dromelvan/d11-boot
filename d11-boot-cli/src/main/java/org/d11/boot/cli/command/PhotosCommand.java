package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.d11.boot.jms.message.UpdatePlayerPhotosRequestMessage;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Command for downloading player photos from PremierLeague.com.
 */
@Component
@Command(name = "photos", mixinStandardHelpOptions = true)
public class PhotosCommand implements Callable<Integer> {

    /**
     * Producer template for sending a file to the update squads route.
     */
    @Produce("{{app.route.updatePhotos}}")
    private ProducerTemplate producerTemplate;

    @Override
    public Integer call() {
        this.producerTemplate.sendBody(new UpdatePlayerPhotosRequestMessage());
        return 0;
    }

}
