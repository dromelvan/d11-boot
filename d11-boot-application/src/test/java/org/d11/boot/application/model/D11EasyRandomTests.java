package org.d11.boot.application.model;

import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.model.jpa.D11Entity;
import org.d11.boot.application.util.MappingProvider;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides random object generator to tests.
 */
public class D11EasyRandomTests extends MappingProvider {

    /**
     * Default number of generated objects that can be used for calls to generate(Class, count).
     */
    public static final int DEFAULT_GENERATED_LIST_SIZE = 10;

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
        return this.d11EasyRandom.nextObject(clazz);
    }

    /**
     * Generates a number of random objects of a D11 entity class.
     *
     * @param clazz Generic class parameter.
     * @param count The number of objects that will be generated.
     * @param <U> The class of objects that will be generated.
     * @return A list of objects of the specified class with the specified length.
     */
    protected <U extends D11Entity> List<U> generate(final Class<U> clazz, final int count) {
        return this.d11EasyRandom
                .objects(clazz, count)
                .collect(Collectors.toList());
    }

}
