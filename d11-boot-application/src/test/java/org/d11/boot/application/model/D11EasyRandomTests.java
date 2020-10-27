package org.d11.boot.application.model;

import org.d11.boot.application.mock.D11EasyRandom;

/**
 * Provides random object generator to tests.
 */
public class D11EasyRandomTests {

    /**
     * Random object generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();

    /**
     * Generates a single object of a D11 entity class.
     *
     * @param clazz Generic class parameter.
     * @param <T> The class of the object that will be generated.
     * @return An object of the specified class.
     */
    protected <T extends D11Entity> T generate(final Class<T> clazz) {
        return this.d11EasyRandom
                .nextObject(clazz);
    }

}
