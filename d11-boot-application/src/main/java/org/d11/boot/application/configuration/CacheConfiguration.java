package org.d11.boot.application.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Enables caching and provides a cache manager.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    /**
     * Name of the user details cache.
     */
    public static final String USER_DETAILS_CACHE = "USER_DETAILS_CACHE";

    /**
     * Provides a Caffeine cache manager.
     *
     * @return A Caffeine cache manager.
     */
    @Bean
    public CacheManager cacheManager() {
        final Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.HOURS);
        final CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(Arrays.asList(USER_DETAILS_CACHE));
        return caffeineCacheManager;
    }

}
