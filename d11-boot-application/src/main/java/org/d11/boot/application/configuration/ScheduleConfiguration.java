package org.d11.boot.application.configuration;

import org.d11.boot.application.service.camel.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Configures scheduling of updating services.
 */
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    /**
     * Provides scheduled services.
     */
    private final ScheduleService scheduleService;

    /**
     * Creates a new configuration.
     *
     * @param scheduleService The scheduler service the configuration will use.
     */
    @Autowired
    public ScheduleConfiguration(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Schedule for updating match datetimes.
     */
    @Scheduled(cron = "${app.schedule.updateMatchDatetimes}")
    public void updateMatchDatetimes() {
        this.scheduleService.updateMatchDatetimes();
    }

    /**
     * Schedule for updating match stats.
     */
    @Scheduled(cron = "${app.schedule.updateMatchStats}")
    public void updateMatchStats() {
        this.scheduleService.updateMatchStats();
    }

    /**
     * Schedule for finishing match stats.
     */
    @Scheduled(cron = "${app.schedule.finishMatchStats}")
    public void finishMatchStats() {
        this.scheduleService.finishMatchStats();
    }

    /**
     * Schedule for updating team squads.
     */
    @Scheduled(cron = "${app.schedule.updateSquads}")
    public void updateSquads() {
        this.scheduleService.updateSquads();
    }

}
