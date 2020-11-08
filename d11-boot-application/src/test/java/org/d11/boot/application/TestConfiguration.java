package org.d11.boot.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Test configuration that provides test classes with access to API service classes.
 */
@Configuration
@ComponentScan(basePackages = "org.d11.boot.api.service")
public class TestConfiguration {

}
