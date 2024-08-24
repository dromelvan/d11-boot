package org.d11.boot.util.mapper.modelmapper;

import org.d11.boot.util.mapper.Mapper;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Model mapper implementation of the Mapper interface.
 */
public class ModelMapperMapper extends ModelMapper implements Mapper {

    /**
     * Set of names of properties that should not be mapped.
     */
    private static final Set<String> IGNORE_PROPERTIES = Set.of("createdAt", "updatedAt");

    /**
     * Creates a mapper and updates configuration.
     */
    public ModelMapperMapper() {
        final Condition<Object, Object> condition =
                context -> !IGNORE_PROPERTIES.contains(context.getMapping().getLastDestinationProperty().getName());

        getConfiguration().setPropertyCondition(condition);
    }

    @Override
    public <T> T map(final Object object, final Class<T> destinationClass) {
        return super.map(object, destinationClass);
    }

    @Override
    public <U, T> List<U> map(final List<T> objects, final Class<U> clazz) {
        return objects.stream()
            .map(entity -> map(entity, clazz))
            .collect(Collectors.toList());
    }

}
