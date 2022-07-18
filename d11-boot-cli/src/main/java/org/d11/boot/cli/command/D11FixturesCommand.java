package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

/**
 * Command for generating a migration SQL file with D11 fixtures for the latest season..
 */
@Component
@Command(name = "d11fixtures", mixinStandardHelpOptions = true)
public class D11FixturesCommand extends AbstractCliCommand {

    /**
     * Producer template for sending files to the D11 fixtures route.
     */
    @Produce("{{app.route.d11Fixtures}}")
    private ProducerTemplate producerTemplate;

    @Override
    public Integer call() {
        this.producerTemplate.sendBody(true);
        return 0;
    }

}
