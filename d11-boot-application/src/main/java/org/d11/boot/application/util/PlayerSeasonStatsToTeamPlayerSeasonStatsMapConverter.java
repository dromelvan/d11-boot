package org.d11.boot.application.util;

import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Team;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a list of player season stats to a list of maps with 'team' and 'players' values.
 */
public class PlayerSeasonStatsToTeamPlayerSeasonStatsMapConverter implements Converter<List<PlayerSeasonStat>, List<Map<String, Object>>> {

    @Override
    public List<Map<String, Object>> convert(final List<PlayerSeasonStat> playerSeasonStats) {
        final Map<Team, List<PlayerSeasonStat>> map = new LinkedHashMap<>();

        for(final PlayerSeasonStat playerSeasonStat : playerSeasonStats) {
            final List<PlayerSeasonStat> teamPlayerSeasonStats = map.computeIfAbsent(playerSeasonStat.getTeam(), team -> new ArrayList<>());
            teamPlayerSeasonStats.add(playerSeasonStat);
        }

        final List<Map<String, Object>> result = new ArrayList<>();

        for(final Map.Entry<Team, List<PlayerSeasonStat>> entry : map.entrySet()) {
            final Map<String, Object> teamMap = new LinkedHashMap<>();
            teamMap.put("team", entry.getKey());
            teamMap.put("players", entry.getValue());
            result.add(teamMap);
        }

        return result;
    }

}
