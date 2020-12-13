package org.d11.boot.application.util;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.PlayerMatchStat;
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
        addMappings(new PropertyMap<MatchWeek, MatchWeekDTO>() {
            @Override
            protected void configure() {
                // This causes Illegal Reflective Access warnings on Java 9+.
                // See https://github.com/modelmapper/modelmapper/issues/414.
                // Run with -Djdk.module.illegalAccess=deny to suppress warnings until
                // ModelMapper hopefully fixes it.
                using(new MatchesByDateConverter()).map(source.getMatches()).setMatches(null);
            }
        });

        addMappings(new PropertyMap<D11MatchWeek, D11MatchWeekDTO>() {
            @Override
            protected void configure() {
                using(new D11MatchesByDateConverter()).map(source.getD11Matches()).setD11Matches(null);
            }
        });

        addMappings(new PropertyMap<PlayerMatchStat, PlayerMatchStatDTO>() {
            @Override
            protected void configure() {
                using(new LineupMapperConverter()).map(source.getLineup()).setLineup(null);
            }
        });
    }

}
