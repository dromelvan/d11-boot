package org.d11.boot.interfaces.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests that the Spring context loads.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContextLoadsTest {

    /**
     * Checks that there are no exception when Loading the Spring context.
     *
     * @param applicationContext Application context that should not be null if the context has loaded.
     */
    @Test
    void contextLoads(final ApplicationContext applicationContext) {
        assertNotNull(applicationContext);
    }

}
