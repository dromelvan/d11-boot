package org.d11.boot.interfaces.rest.v2.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.servlet.http.HttpServletRequest;
import org.d11.boot.api.v2.model.BadRequestResponseBodyDTO;
import org.d11.boot.api.v2.model.ConflictResponseBodyDTO;
import org.d11.boot.api.v2.model.NotFoundResponseBodyDTO;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        final BadRequestResponseBodyDTO badRequestResponseBodyDTO = responseEntity.getBody();

        assertNotNull(badRequestResponseBodyDTO);
        assertNotNull(badRequestResponseBodyDTO.getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), badRequestResponseBodyDTO.getError());
        assertEquals(httpServletRequest.getMethod(), badRequestResponseBodyDTO.getMethod());
        assertEquals(httpServletRequest.getRequestURI(), badRequestResponseBodyDTO.getPath());

        assertEquals(1, badRequestResponseBodyDTO.getValidationErrors().size());
        assertEquals(e.getParameter(), badRequestResponseBodyDTO.getValidationErrors().get(0).getProperty());
        assertEquals(e.getMessage(), badRequestResponseBodyDTO.getValidationErrors().get(0).getError());
    }

    /**
     * Tests ControllerExceptionHandlerV2::handle(UnauthorizedException).
     */
    @Test
    void testHandleUnauthorizedException() {
        final UnauthorizedException e = new UnauthorizedException();

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        final ResponseEntity<?> responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    /**
     * Tests ControllerExceptionHandlerV2::handle(ForbiddenException).
     */
    @Test
    void testHandleForbiddenException() {
        final ForbiddenException e = new ForbiddenException();

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        final ResponseEntity<?> responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    /**
     * Tests ControllerExceptionHandlerV2::handle(NotFoundException).
     */
    @Test
    void testHandleNotFoundException() {
        final NotFoundException e = new NotFoundException(1L, NotFoundException.class);

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        final ResponseEntity<NotFoundResponseBodyDTO> responseEntity =
                controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        final NotFoundResponseBodyDTO notFoundResponseBodyDTO = responseEntity.getBody();

        assertNotNull(notFoundResponseBodyDTO);

        assertNotNull(notFoundResponseBodyDTO.getTimestamp());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), notFoundResponseBodyDTO.getError());
        assertEquals(e.getResource(), notFoundResponseBodyDTO.getResource());
        assertEquals(e.getId(), notFoundResponseBodyDTO.getId());
        assertEquals(httpServletRequest.getMethod(), notFoundResponseBodyDTO.getMethod());
        assertEquals(httpServletRequest.getRequestURI(), notFoundResponseBodyDTO.getPath());
    }

    /**
     * Tests ControllerExceptionHandlerV2::handle(ConflictException).
     */
    @Test
    void testHandleConflictException() {
        final ConflictException e = new ConflictException("Conflict");

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        final ResponseEntity<ConflictResponseBodyDTO> responseEntity =
                controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        final ConflictResponseBodyDTO conflictResponseBodyDTO = responseEntity.getBody();

        assertNotNull(conflictResponseBodyDTO);

        assertNotNull(conflictResponseBodyDTO.getTimestamp());
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), conflictResponseBodyDTO.getError());
        assertEquals(e.getMessage(), conflictResponseBodyDTO.getMessage());
        assertEquals(httpServletRequest.getMethod(), conflictResponseBodyDTO.getMethod());
        assertEquals(httpServletRequest.getRequestURI(), conflictResponseBodyDTO.getPath());
    }

    /**
     * Tests ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException).
     */
    @Test
    @SuppressFBWarnings(value = "NP",
                        justification = "We know there's no NullPointerException here")
    void testHandleMethodArgumentTypeMismatchException() {
        final MethodArgumentTypeMismatchException e = mock(MethodArgumentTypeMismatchException.class);

        when(e.getPropertyName()).thenReturn(MethodArgumentTypeMismatchException.class.getName());
        when(e.getCause()).thenReturn(null);

        final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn(REQUEST_URI);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        final ControllerExceptionHandlerV2 controllerExceptionHandlerV2 = new ControllerExceptionHandlerV2();

        ResponseEntity<BadRequestResponseBodyDTO> responseEntity =
                controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        final BadRequestResponseBodyDTO badRequestResponseBodyDTO = responseEntity.getBody();

        assertNotNull(badRequestResponseBodyDTO);

        assertNotNull(badRequestResponseBodyDTO.getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), badRequestResponseBodyDTO.getError());
        assertEquals(httpServletRequest.getMethod(), badRequestResponseBodyDTO.getMethod());
        assertEquals(httpServletRequest.getRequestURI(), badRequestResponseBodyDTO.getPath());

        assertEquals(1, badRequestResponseBodyDTO.getValidationErrors().size());
        assertEquals(e.getPropertyName(), badRequestResponseBodyDTO.getValidationErrors().get(0).getProperty());
        assertEquals(e.getMessage(), badRequestResponseBodyDTO.getValidationErrors().get(0).getError());

        when(e.getCause()).thenReturn(new NumberFormatException());

        responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertNotNull(responseEntity.getBody());
        assertEquals("is invalid", responseEntity.getBody().getValidationErrors().get(0).getError());

        final NullPointerException nullPointerException = mock(NullPointerException.class);

        when(nullPointerException.getMessage()).thenReturn("NullPointerException");
        when(e.getCause()).thenReturn(nullPointerException);

        responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertNotNull(responseEntity.getBody());
        assertEquals(nullPointerException.getMessage(),
                     responseEntity.getBody().getValidationErrors().get(0).getError());
    }

}
