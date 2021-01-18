package org.d11.boot.api.service;

import org.d11.boot.api.model.SearchResultDTO;
import org.d11.boot.client.api.SearchApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides search API services.
 */
@Service
public class SearchApiService extends D11ApiService {

    /**
     * Searches players with matching names.
     *
     * @param name Name that will be matched with players.
     * @return Search result with players with matching names.
     */
    public SearchResultDTO search(final String name) {
        try {
            final SearchApi searchApi = new SearchApi(getApiClient());
            return searchApi.search(name).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
