package org.d11.boot.cli.command;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * Command for updating D11 squads according to player data from PremierLeague.com.
 */
@Component
@Command(name = "squads", mixinStandardHelpOptions = true)
public class UpdateSquadsCommand implements Callable<Integer> {

    /**
     * Producer template for sending a file to the update squads route.
     */
    @Produce("{{app.route.updateSquads}}")
    private ProducerTemplate producerTemplate;

    @Override
    public Integer call() {
        // The route just needs a dummy body to start.
        this.producerTemplate.sendBody(true);
        return 0;
    }

}