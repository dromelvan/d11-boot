package org.d11.boot.api.service;

import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.client.api.PlayerMatchStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides player match stat API services.
 */
@Service
public class PlayerMatchStatApiService extends D11ApiService {

    /**
     * Finds a player match stat with a specific id.
     *
     * @param playerMatchStatId The id of the player match stat that will be looked up.
     * @return Player match stat DTO for the specified id or null if no player match stat was found.
     */
    public PlayerMatchStatDTO findPlayerMatchStatById(Long playerMatchStatId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findPlayerMatchStatById(playerMatchStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player match stats for a specific match.
     *
     * @param matchId The id of the match for which player match stats will be looked up.
     * @return Player match stat DTOs for the specified match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByMatchId(Long matchId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findPlayerMatchStatByMatchId(matchId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds active player match stats for a specific match and team.
     *
     * @param matchId The id of the match for which player match stats will be looked up.
     * @param teamId The id of the team for which player match stats will be looked up.
     * @return Active player match stat DTOs for the specified match and team.
     */
    public List<PlayerMatchStatDTO> findActivePlayerMatchStatByMatchIdAndTeamId(Long matchId, Long teamId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findActivePlayerMatchStatByMatchIdAndTeamId(matchId, teamId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player match stats for a specific D11 match.
     *
     * @param d11MatchId The id of the D11 match for which player match stats will be looked up.
     * @return Player match stat DTOs for the specified D11 match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByD11MatchId(Long d11MatchId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findPlayerMatchStatByD11MatchId(d11MatchId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player match stats for a specific D11 match and D11 team.
     *
     * @param d11MatchId The id of the D11 match for which player match stats will be looked up.
     * @param d11TeamId The id of the D11 team for which player match stats will be looked up.
     * @return Active player match stat DTOs for the specified D11 match and D11 team.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByD11MatchIdAndD11TeamId(Long d11MatchId, Long d11TeamId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findPlayerMatchStatByD11MatchIdAndD11TeamId(d11MatchId, d11TeamId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player match stats for a player and a season.
     *
     * @param playerId The id of the player for which player match stats will be looked up.
     * @param seasonId The id of the season for which player match stats will be looked up.
     * @return Player match stat DTOs for the specified D11 match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByPlayerIdAndSeasonId(Long playerId, Long seasonId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findPlayerMatchStatByPlayerIdAndSeasonId(playerId, seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the top 5 player match stats for a match week ordered by points and rating.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @return The top 5 player match stat DTOs for the specified match week.
     */
    public List<PlayerMatchStatDTO> findTop5PlayerMatchStatByMatchWeek(Long matchWeekId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findTop5PlayerMatchStatByMatchWeek(matchWeekId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the bottom 5 player match stats for a match week ordered by points and rating.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @return The bottome 5 player match stat DTOs for the specified match week.
     */
    public List<PlayerMatchStatDTO> findBottom5PlayerMatchStatByMatchWeek(Long matchWeekId) {
        try {
            final PlayerMatchStatApi playerMatchStatApi = new PlayerMatchStatApi(getApiClient());
            return playerMatchStatApi.findBottom5PlayerMatchStatByMatchWeek(matchWeekId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
