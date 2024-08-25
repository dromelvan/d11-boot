package org.d11.boot.spring;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.spring.util.D11BootEasyRandomParameters;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for test classes.
 */
public class EasyRandomTests {

    /**
     * Default number of generated objects for calls to generateList(Class).
     */
    protected static final int DEFAULT_GENERATED_LIST_SIZE = 10;

    /**
     * EasyRandom for generating test objects.
     */
    @Getter(AccessLevel.PROTECTED)
    private static final EasyRandom EASY_RANDOM = new EasyRandom(new D11BootEasyRandomParameters());

    /**
     * Generates a single object of a class.
     *
     * @param clazz Generic class parameter.
     * @param <T> The class of the object that will be generated.
     * @return An object of the specified class.
     */
    protected <T> T generate(final Class<T> clazz) {
        return EASY_RANDOM.nextObject(clazz);
    }

    /**
     * Generates a number of random objects of a lass.
     *
     * @param clazz Generic class parameter.
     * @param <U> The class of objects that will be generated.
     * @return A list of objects of the specified class with the specified length.
     */
    protected <U> List<U> generateList(final Class<U> clazz) {
        return EASY_RANDOM
                .objects(clazz, DEFAULT_GENERATED_LIST_SIZE)
                .collect(Collectors.toList());
    }

    /**
     * Generates a number of random objects of a class.
     *
     * @param clazz Generic class parameter.
     * @param count The number of objects that will be generated.
     * @param <U> The class of objects that will be generated.
     * @return A list of objects of the specified class with the specified length.
     */
    protected <U> List<U> generateList(final Class<U> clazz, final int count) {
        return EASY_RANDOM
                .objects(clazz, count)
                .collect(Collectors.toList());
    }

}
