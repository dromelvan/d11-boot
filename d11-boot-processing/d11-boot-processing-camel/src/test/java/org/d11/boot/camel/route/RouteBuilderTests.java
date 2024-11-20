package org.d11.boot.camel.route;

import jakarta.validation.ConstraintViolation;
import lombok.SneakyThrows;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.d11.boot.camel.Route;

import java.util.Set;

/**
 * Base class for route builder tests.
 */
public class RouteBuilderTests extends CamelTestSupport {

    /**
     * Mocked endpoint prefix.
     */
    protected static final String MOCK = "mock:";

    /**
     * Mocks an endpoint for a route.
     *
     * @param route    The route definition.
     * @param endpoint The endpoint URI.
     * @return Mocked endpoint for the route and the endpoint.
     */
    @SneakyThrows
    protected MockEndpoint mockEndpoint(final Route route, final String endpoint) {
        final MockEndpoint mockEndpoint = getMockEndpoint(MOCK + endpoint);

        AdviceWith.adviceWith(this.context, route.getId(), advice ->
                advice.interceptSendToEndpoint(endpoint)
                        .skipSendToOriginalEndpoint()
                        .to(MOCK + endpoint)
        );

        return mockEndpoint;
    }

    /**
     * Gets a constraint violation property name from an exception. The exception must be a BeanValidationException and
     * have exactly 1 constraint violation.
     *
     * @param e The exception
     * @return The property name of the one and only constraint violation in the exception.
     */
    protected String getConstraintViolationPropertyName(final Exception e) {
        if (!(e instanceof BeanValidationException exception)) {
            throw new IllegalArgumentException("Exception is not a bean validation exception");
        }

        final Set<ConstraintViolation<Object>> constraintViolations = exception.getConstraintViolations();
        final int size = constraintViolations.size();

        if (size != 1) {
            throw new IllegalArgumentException("BeanValidationException has " + size + " constraint violations");
        }

        return constraintViolations.iterator().next().getPropertyPath().toString();
    }

    /**
     * Sends a body to a route.
     *
     * @param route The route.
     * @param body  The body.
     * @return The route exchange.
     */
    protected Exchange send(final Route route, final Object body) {
        final Exchange in = getMandatoryEndpoint(route.getEndpoint()).createExchange(ExchangePattern.InOut);
        in.getIn().setBody(body);

        return this.template.send(route.getEndpoint(), in);
    }

}
