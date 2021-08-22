package org.d11.boot.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Spring application context tests.
 */
@SpringBootTest
@ActiveProfiles("test")
public class D11BootApplicationTests {

    /**
     * Context loading test.
     */
    @Test
    @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
    public void contextLoads() {
        // Starts up the Spring application context to check many things that will result in errors on startup.
    }

}
