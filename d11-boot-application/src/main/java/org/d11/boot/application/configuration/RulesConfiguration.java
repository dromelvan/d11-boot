package org.d11.boot.application.configuration;

import org.d11.boot.application.model.util.PlayerMatchStatPointsCalculator;
import org.d11.boot.application.model.util.PlayerMatchStatPointsCalculatorV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures rules properties.
 */
@Configuration
public class RulesConfiguration {

    /**
     * Provides the current implementation of PlayerMatchStatPointsCalculator.
     *
     * @return The current implementation of PlayerMatchStatPointsCalculator.
     */
    @Bean
    public PlayerMatchStatPointsCalculator playerMatchStatPointsCalculator() {
        return new PlayerMatchStatPointsCalculatorV1();
    }

}
