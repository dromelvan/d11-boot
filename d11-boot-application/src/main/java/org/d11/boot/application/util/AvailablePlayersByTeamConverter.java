package org.d11.boot.application.util;

import org.d11.boot.api.model.AvailablePlayerDTO;
import org.d11.boot.api.model.AvailablePlayersTeamDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a list of available player DTOs to a list of available players team DTOs.
 */
public class AvailablePlayersByTeamConverter implements Converter<List<AvailablePlayerDTO>, List<AvailablePlayersTeamDTO>> {

    @Override
    public List<AvailablePlayersTeamDTO> convert(final List<AvailablePlayerDTO> availablePlayers) {
        final Map<Long, AvailablePlayersTeamDTO> map = new LinkedHashMap<>();

        availablePlayers.sort(Comparator.comparing(AvailablePlayerDTO::getTeamName)
                .thenComparing(AvailablePlayerDTO::getPositionSortOrder)
                .thenComparing(AvailablePlayerDTO::getPlayerName));

        for(final AvailablePlayerDTO availablePlayerDTO : availablePlayers) {
            AvailablePlayersTeamDTO availablePlayersTeamDTO = map.get(availablePlayerDTO.getTeamId());
            if(availablePlayersTeamDTO == null) {
                availablePlayersTeamDTO = new AvailablePlayersTeamDTO()
                        .id(availablePlayerDTO.getTeamId())
                        .name(availablePlayerDTO.getTeamName());
                map.put(availablePlayersTeamDTO.getId(), availablePlayersTeamDTO);
            }
            availablePlayersTeamDTO.addPlayersItem(availablePlayerDTO);
        }
        return new ArrayList<>(map.values());
    }

}
