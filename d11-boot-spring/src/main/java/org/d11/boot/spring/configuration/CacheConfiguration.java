package org.d11.boot.spring.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * At the moment we're only using this configuration to enable caching. The actual caching is autoconfigured in the
 * application.yaml properties.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

}
