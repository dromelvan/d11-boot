package org.d11.boot.application.service;

import lombok.Getter;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.application.util.MappingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for Spring Boot service tests.
 *
 * @param <T> Service class that will be tested.
 * @param <U> Repository class for getting test data.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ServiceTests<T extends D11BootService, U extends D11EntityRepository<?>> extends MappingProvider {

    /**
     * Service that will be tested.
     */
    @Getter
    @Autowired
    private T d11BootService;
    /**
     * Repository for getting test data.
     */
    @Getter
    @Autowired
    private U d11EntityRepository;

}
