package org.d11.boot.application.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for classes that want to map objects between entity and DTO classes.
 */
public class MappingProvider {

    /**
     * ModelMapper for doing the actual mapping.
     */
    private final ModelMapper modelMapper = new D11BootModelMapper();

    /**
     * Sets the matching strategy the matcher should use.
     *
     * @param matchingStrategy The matching strategy the matcher should use.
     */
    protected void setMatchingStrategy(final MatchingStrategy matchingStrategy) {
        this.modelMapper.getConfiguration().setMatchingStrategy(matchingStrategy);
    }

    /**
     * Maps an object to a destination class.
     *
     * @param object The object to map.
     * @param destinationClass The class the object will be mapped to.
     * @param <T> The class the object will be mapped to.
     * @return The object mapped to the destination class.
     */
    protected <T> T map(final Object object, final Class<T> destinationClass) {
        return this.modelMapper.map(object, destinationClass);
    }

    /**
     * Maps a source object to a destination object.
     *
     * @param source      The object from which values will be copied.
     * @param destination The object to which values will be copied;
     * @param <T>         Type of the destination object.
     * @return A mapped destination type object.
     */
    protected <T> T map(final Object source, final T destination) {
        this.modelMapper.map(source, destination);
        return destination;
    }

    /**
     * Maps a list of objects to a list of objects in a destination class.
     *
     * @param objects The list of objects to map.
     * @param clazz The class the objects will be mapped to.
     * @param <U> The class the objects will be mapped to.
     * @param <T> The class of the objects to be map.
     * @return List of objects mapped to the destination class.
     */
    protected <U, T> List<U> map(final List<T> objects, final Class<U> clazz) {
        return objects.stream()
                .map(entity -> this.modelMapper.map(entity, clazz))
                .collect(Collectors.toList());
    }

}
