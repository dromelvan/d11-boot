package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base interface for database repositories.
 *
 * @param <T> The entity class this repository will handle.
 */
@NoRepositoryBean
public interface D11EntityRepository<T extends D11Entity> extends JpaRepository<T, Long> {

}
