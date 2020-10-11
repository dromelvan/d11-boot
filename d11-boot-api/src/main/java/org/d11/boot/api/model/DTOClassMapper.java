package org.d11.boot.api.model;

/**
 * Maps a class to its corresponding DTO class.
 */
public class DTOClassMapper {

    /**
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> getDTOClass(Class<?> clazz) throws ClassNotFoundException {
        Package modelPackage = getClass().getPackage();
        return Class.forName(modelPackage.getName() + "." + clazz.getSimpleName() + "DTO");
    }

}
