package org.d11.boot.camel;

import lombok.Getter;

/**
 * Camel route definitions.
 */
public enum Route {

    /**
     * Receive test route definition.
     */
    RECEIVE_TEST("direct:receiveTest"),
    /**
     * Receive test route definition.
     */
    HANDLE_TEST("direct:handleTest"),
    /**
     * Create transfer window route definition.
     */
    CREATE_TRANSFER_WINDOW("direct:createTransferWindow");

    /**
     * Route endpoint string.
     */
    @Getter
    private final String endpoint;

    /**
     * Creates a new route.
     *
     * @param endpoint Route endpoint string.
     */
    Route(final String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Returns the route id.
     *
     * @return The route id.
     */
    public String getId() {
        return toString();
    }

}
