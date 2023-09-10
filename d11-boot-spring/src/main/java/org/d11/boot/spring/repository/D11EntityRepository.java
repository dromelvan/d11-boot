package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base interface for entity repositories.
 *
 * @param <T> The entity class the repository will handle.
 */
@NoRepositoryBean
public interface D11EntityRepository<T extends D11Entity> extends JpaRepository<T, Long> {

}
