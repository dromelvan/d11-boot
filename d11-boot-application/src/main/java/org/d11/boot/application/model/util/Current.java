package org.d11.boot.application.model.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Component that provides static access to various current entities.
 */
@Component
@SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
public final class Current implements ApplicationContextAware {

    /**
     * Application context that provides repositories.
     */
    private static ApplicationContext applicationContext;

    /**
     * Gets the current season.
     *
     * @return The current season.
     */
    public static Season getSeason() {
        return Current.applicationContext.getBean(SeasonRepository.class).findFirstByOrderByDateDesc()
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Gets the current transfer window.
     *
     * @return The current transfer window.
     */
    public static TransferWindow getTransferWindow() {
        return Current.applicationContext.getBean(TransferWindowRepository.class).findFirstByOrderByDatetimeDesc()
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Gets the current transfer day.
     *
     * @return The current transfer day.
     */
    public static TransferDay getTransferDay() {
        return Current.applicationContext.getBean(TransferDayRepository.class).findFirstByOrderByDatetimeDesc()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        Current.applicationContext = applicationContext;
    }

}
