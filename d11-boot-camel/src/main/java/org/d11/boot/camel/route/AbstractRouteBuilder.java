package org.d11.boot.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.OnExceptionDefinition;

/**
 * Base class for route builders.
 */
public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Override
    public abstract void configure();

    /**
     * Configures basic onException handling for a route.
     *
     * @return OnExceptionDefinition that can be used to further configure onException handling.
     */
    protected OnExceptionDefinition configureOnException() {
        return onException(Exception.class)
                .log(LoggingLevel.ERROR, "${exception.message}")
                .log(LoggingLevel.DEBUG, "${exception.stacktrace}");
    }

}
