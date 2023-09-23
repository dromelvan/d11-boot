package org.d11.boot.util.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.util.mapper.modelmapper.ModelMapperMapper;

import java.util.List;

/**
 * Base class for classes that need access to an implementation of Mapper.
 */
public class MapperProvider {

    /**
     * Mapper implementation.
     */
    @Getter(AccessLevel.PROTECTED)
    private final Mapper mapper = new ModelMapperMapper();

    /**
     * Maps an object to a new object of a destination class.
     *
     * @param object           The object to map.
     * @param destinationClass The class the object will be mapped to.
     * @param <T>              The class the object will be mapped to.
     * @return The object mapped to the destination class.
     */
    protected <T> T map(final Object object, final Class<T> destinationClass) {
        return this.mapper.map(object, destinationClass);
    }

    /**
     * Maps a source object to a destination object.
     *
     * @param source      The object from which values will be copied.
     * @param destination The object to which values will be copied;
     */
    protected void map(final Object source, final Object destination) {
        this.mapper.map(source, destination);
    }

    /**
     * Maps a list of objects to a list of new objects in a destination class.
     *
     * @param objects The list of objects to map.
     * @param clazz   The class the objects will be mapped to.
     * @param <U>     The class the objects will be mapped to.
     * @param <T>     The class of the objects to be map.
     * @return List of objects mapped to the destination class.
     */
    protected <U, T> List<U> map(final List<T> objects, final Class<U> clazz) {
        return this.mapper.map(objects, clazz);
    }

}
