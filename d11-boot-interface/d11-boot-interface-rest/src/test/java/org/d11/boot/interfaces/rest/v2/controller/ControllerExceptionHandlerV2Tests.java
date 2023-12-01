package org.d11.boot.interfaces.rest.v2.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.d11.boot.api.v2.model.BadRequestResponseBodyDTO;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Controller exception handler tests.
 */
class ControllerExceptionHandlerV2Tests {

    /**
     * HTTP servlet request method.
     */
    private static final String METHOD = "method";

    /**
     * Request URI.
     */
    private static final String REQUEST_URI = "https://foo.bar.fi";

    /**
     * Tests ControllerExceptionHandlerV2::handle(BadRequestException).
     */
    @Test
    void testHandleBadRequestException() {
        final BadRequestException e = new BadRequestException("parameter", "parameterError");

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        final ResponseEntity<BadRequestResponseBodyDTO> responseEntity =
                controllerExceptionHandlerV2.handle(e, httpServletRequest);
        final BadRequestResponseBodyDTO badRequestResponseBodyDTO = responseEntity.getBody();

        assertNotNull(badRequestResponseBodyDTO, "ControllerExceptionHandlerV2::handle(BadRequestException) not null");
        assertNotNull(badRequestResponseBodyDTO.getTimestamp(),
                      "ControllerExceptionHandlerV2::handle(BadRequestException) timestamp not null");
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), badRequestResponseBodyDTO.getError(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) error equals");
        assertEquals(httpServletRequest.getMethod(), badRequestResponseBodyDTO.getMethod(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) method equals");
        assertEquals(httpServletRequest.getRequestURI(), badRequestResponseBodyDTO.getPath(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) path equals");

        assertEquals(1, badRequestResponseBodyDTO.getValidationErrors().size(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) validationErrors size equals");
        assertEquals(e.getParameter(), badRequestResponseBodyDTO.getValidationErrors().get(0).getProperty(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) validationError property equals");
        assertEquals(e.getMessage(), badRequestResponseBodyDTO.getValidationErrors().get(0).getError(),
                     "ControllerExceptionHandlerV2::handle(BadRequestException) validationError error equals");
    }

}
