package org.d11.boot.camel.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * Base class for route builders.
 */
public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Override
    public abstract void configure();

}
