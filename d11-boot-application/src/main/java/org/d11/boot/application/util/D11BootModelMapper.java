package org.d11.boot.application.util;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.model.SeasonSummaryDTO;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Season;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * ModelMapper with adaptations for the D11 Boot application.
 */
public class D11BootModelMapper extends ModelMapper {

    /**
     * Creates a new mapper.
     */
    public D11BootModelMapper() {
        addConverter(new LineupMapperConverter());

        addMappings(new PropertyMap<MatchWeek, MatchWeekDTO>() {
            @Override
            protected void configure() {
                using(new MatchesByDateMapperConverter()).map(source.getMatches()).setMatches(null);
                map(source.getPremierLeague().getSeason()).setSeason(null);
            }
        });
        addMappings(new PropertyMap<MatchWeekDTO, MatchWeek>() {
            @Override
            protected void configure() {
                skip(destination.getMostValuablePlayer());
            }
        });

        addMappings(new PropertyMap<D11MatchWeek, D11MatchWeekDTO>() {
            @Override
            protected void configure() {
                using(new D11MatchesByDateMapperConverter()).map(source.getD11Matches()).setD11Matches(null);
                map(source.getMatchWeek().getMostValuablePlayer()).setMostValuablePlayer(null);
            }
        });

        addMappings(new PropertyMap<Season, SeasonSummaryDTO>() {
            @Override
            protected void configure() {
                map(source.getD11League().getTop3D11TeamTableStats()).setTop3D11TeamTableStats(null);
            }
        });

    }

}
