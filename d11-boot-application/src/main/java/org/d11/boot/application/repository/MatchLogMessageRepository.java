package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.MatchLogMessage;
import org.springframework.stereotype.Repository;

/**
 * Repository for match log message entities.
 */
@Repository
public interface MatchLogMessageRepository extends D11EntityRepository<MatchLogMessage> {

}
