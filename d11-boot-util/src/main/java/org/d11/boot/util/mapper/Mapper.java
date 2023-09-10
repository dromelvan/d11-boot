package org.d11.boot.util.mapper;

import java.util.List;

/**
 * Interface for mapping properties between objects.
 */
public interface Mapper {

    /**
     * Maps an object to a new object of a destination class.
     *
     * @param object           The object to map.
     * @param destinationClass The class the object will be mapped to.
     * @param <T>              The class the object will be mapped to.
     * @return The object mapped to the destination class.
     */
    <T> T map(Object object, Class<T> destinationClass);

    /**
     * Maps a source object to a destination object.
     *
     * @param source      The object from which values will be copied.
     * @param destination The object to which values will be copied;
     */
    void map(Object source, Object destination);

    /**
     * Maps a list of objects to a list of new objects in a destination class.
     *
     * @param objects The list of objects to map.
     * @param clazz   The class the objects will be mapped to.
     * @param <U>     The class the objects will be mapped to.
     * @param <T>     The class of the objects to be map.
     * @return List of objects mapped to the destination class.
     */
    <U, T> List<U> map(List<T> objects, Class<U> clazz);

}
