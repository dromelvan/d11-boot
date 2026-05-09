package org.d11.boot.spring.model;

import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Imported;

/**
 * Projection holding a season and its ranking-1 stat entries.
 *
 * @param season            The season.
 * @param d11TeamSeasonStat The D11 team season stat with ranking 1 for the season.
 * @param teamSeasonStat    The team season stat with ranking 1 for the season.
 * @param playerSeasonStat  The player season stat with ranking 1 for the season.
 */
@Embeddable
@Imported
public record SeasonWinners(
        Season season,
        D11TeamSeasonStat d11TeamSeasonStat,
        TeamSeasonStat teamSeasonStat,
        PlayerSeasonStat playerSeasonStat
) { }
