package org.d11.boot;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.camel.ProducerTemplate;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Command line runner for development/test purposes. Activates by using 'commandline' profile.
 */
@Component
@Profile("commandline")
@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
                    justification = "Can't inject an immutable ProducerTemplate")
public class D11CommandLineRunner implements CommandLineRunner {

    /**
     * Producer template.
     */
    private final ProducerTemplate producerTemplate;

    /**
     * Creates a new D11 command line runner.
     *
     * @param producerTemplate Producer template.
     */
    @Autowired
    public D11CommandLineRunner(final ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public void run(final String... args) {
        final CreateTransferWindowExchangeBody body = new CreateTransferWindowExchangeBody();
        body.setDatetime(LocalDateTime.now().plusMinutes(1));
        body.setTransferDayDelay(1);

        this.producerTemplate.sendBody(Route.CREATE_TRANSFER_WINDOW.getEndpoint(), body);
    }

}
