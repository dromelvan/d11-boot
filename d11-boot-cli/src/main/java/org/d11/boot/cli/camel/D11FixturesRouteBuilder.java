package org.d11.boot.cli.camel;

import org.d11.boot.camel.AbstractRouteBuilder;
import org.d11.boot.cli.camel.processor.D11FixturesProcessor;
import org.springframework.stereotype.Component;

/**
 * Builds a route to generate D11 fixtures and save them in a migration file.
 */
@Component
public class D11FixturesRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        from("{{app.route.d11Fixtures}}")
                .routeId("D11_FIXTURES")
                // Body is dummy boolean
                .process(new D11FixturesProcessor())
                // Body is SQL string. Write it to a file.
                .to("file:files/data/d11/fixtures/?fileName=d11Fixtures.sql");
    }

}
