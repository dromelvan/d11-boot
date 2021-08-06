package org.d11.boot.application.util;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TransferDay;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

/**
 * ModelMapper with adaptations for the D11 Boot application.
 */
public class D11BootModelMapper extends ModelMapper {

    /**
     * Creates a new mapper.
     */
    public D11BootModelMapper() {
        getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        addConverter(new LineupMapperConverter());

        addMappings(new PropertyMap<Season, SeasonDTO>() {
            @Override
            protected void configure() {
                using(new D11EntityListMapperConverter()).map(source.getMatchWeeks()).setMatchWeeks(null);
                using(new D11EntityListSizeMapperConverter()).map(source.getPlayerSeasonStats()).setPlayerSeasonStatCount(null);
            }
        });
        addMappings(new PropertyMap<MatchWeek, MatchWeekDTO>() {
            @Override
            protected void configure() {
                using(new MatchesByDateMapperConverter()).map(source.getMatches()).setMatches(null);
                using(new D11MatchesByDateMapperConverter()).map(source.getD11Matches()).setD11Matches(null);
            }
        });
        addMappings(new PropertyMap<MatchWeekDTO, MatchWeek>() {
            @Override
            protected void configure() {
                skip(destination.getPremierLeagueLeader());
                skip(destination.getD11LeagueLeader());
                skip(destination.getMostValuablePlayer());
            }
        });
        addMappings(new PropertyMap<TransferDay, TransferDayDTO>() {
            @Override
            protected void configure() {
                using(new D11EntityListSizeMapperConverter()).map(source.getTransferListings()).setTransferListingCount(null);
            }
        });
    }

}
