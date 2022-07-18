package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Arrays;

/**
 * Command for opening WhoScored fixture files, parsing them and saving migration SQL in a file.
 */
@Component
@Command(name = "fixtures", mixinStandardHelpOptions = true)
public class FixturesCommand extends AbstractCliCommand {

    /**
     * Producer template for sending files to the fixtures route.
     */
    @Produce("{{app.route.fixtures}}")
    private ProducerTemplate producerTemplate;

    @Override
    public Integer call() {
        final File[] files = getFiles(new HtmlFileFilter());
        this.producerTemplate.sendBody(Arrays.asList(files));
        return 0;
    }

}
