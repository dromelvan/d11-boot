package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.io.File;

/**
 * Command for opening a WhoScored match file, parsing it and saving the json output to a destination file.
 */
@Component
@Command(name = "parse", mixinStandardHelpOptions = true)
public class ParseCommand extends AbstractCliCommand {

    /**
     * Producer template for sending a file to the parse route.
     */
    @Produce("{{app.route.parse}}")
    private ProducerTemplate producerTemplate;

    @Override
    public Integer call() {
        final File[] files = getFiles(new HtmlFileFilter());
        for(final File file : files) {
            this.producerTemplate.sendBody(file);
        }
        return 0;
    }

}
