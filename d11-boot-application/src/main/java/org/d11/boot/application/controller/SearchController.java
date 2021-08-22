package org.d11.boot.application.controller;

import org.d11.boot.api.SearchApi;
import org.d11.boot.api.model.SearchResultDTO;
import org.d11.boot.application.service.api.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the SearchApi and provides search endpoints.
 */
@RestController
public class SearchController extends D11BootController implements SearchApi {

    /**
     * The service that will be performing the searches.
     */
    private final SearchService searchService;

    /**
     * Creates a new controller.
     *
     * @param searchService The service that will be used by this controller.
     */
    @Autowired
    public SearchController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public ResponseEntity<SearchResultDTO> search(final String name) {
        final SearchResultDTO searchResultDTO = this.searchService.search(name);
        return ResponseEntity.ok(searchResultDTO);
    }

}
