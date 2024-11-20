package org.d11.boot.camel.bean;

/**
 * Interface that handles a test route body.
 */
public interface HandleTest {

    /**
     * Handles a test route body in whatever way an implementing class does it.
     *
     * @param body The test route body.
     */
    void handle(Object body);

}
