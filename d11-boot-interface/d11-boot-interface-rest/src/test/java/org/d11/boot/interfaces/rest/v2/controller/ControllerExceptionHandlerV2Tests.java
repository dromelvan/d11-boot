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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST,
                     "ControllerExceptionHandlerV2::handle(BadRequestException) status equals");

        final BadRequestResponseBodyDTO badRequestResponseBodyDTO = responseEntity.getBody();

        assertNotNull(badRequestResponseBodyDTO,
                      "ControllerExceptionHandlerV2::handle(BadRequestException) body not null");
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.UNAUTHORIZED,
                     "ControllerExceptionHandlerV2::handle(UnauthorizedException) status equals");
        assertNull(responseEntity.getBody(), "ControllerExceptionHandlerV2::handle(UnauthorizedException) body null");
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.FORBIDDEN,
                     "ControllerExceptionHandlerV2::handle(ForbiddenException) status equals");
        assertNull(responseEntity.getBody(), "ControllerExceptionHandlerV2::handle(ForbiddenException) body null");
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND,
                     "ControllerExceptionHandlerV2::handle(NotFoundException) status equals");

        final NotFoundResponseBodyDTO notFoundResponseBodyDTO = responseEntity.getBody();

        assertNotNull(notFoundResponseBodyDTO, "ControllerExceptionHandlerV2::handle(NotFoundException) body null");

        assertNotNull(notFoundResponseBodyDTO.getTimestamp(),
                      "ControllerExceptionHandlerV2::handle(NotFoundException) timestamp not null");
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), notFoundResponseBodyDTO.getError(),
                     "ControllerExceptionHandlerV2::handle(NotFoundException) error equals");
        assertEquals(e.getResource(), notFoundResponseBodyDTO.getResource(),
                     "ControllerExceptionHandlerV2::handle(NotFoundException) resource equals");
        assertEquals(e.getId(), notFoundResponseBodyDTO.getId(),
                     "ControllerExceptionHandlerV2::handle(NotFoundException) id equals");
        assertEquals(httpServletRequest.getMethod(), notFoundResponseBodyDTO.getMethod(),
                     "ControllerExceptionHandlerV2::handle(NotFoundException) method equals");
        assertEquals(httpServletRequest.getRequestURI(), notFoundResponseBodyDTO.getPath(),
                     "ControllerExceptionHandlerV2::handle(NotFoundException) path equals");
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT,
                     "ControllerExceptionHandlerV2::handle(ConflictException) status equals");

        final ConflictResponseBodyDTO conflictResponseBodyDTO = responseEntity.getBody();

        assertNotNull(conflictResponseBodyDTO, "ControllerExceptionHandlerV2::handle(ConflictException) body null");

        assertNotNull(conflictResponseBodyDTO.getTimestamp(),
                      "ControllerExceptionHandlerV2::handle(ConflictException) timestamp not null");
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), conflictResponseBodyDTO.getError(),
                     "ControllerExceptionHandlerV2::handle(ConflictException) error equals");
        assertEquals(e.getMessage(), conflictResponseBodyDTO.getMessage(),
                     "ControllerExceptionHandlerV2::handle(ConflictException) message equals");
        assertEquals(httpServletRequest.getMethod(), conflictResponseBodyDTO.getMethod(),
                     "ControllerExceptionHandlerV2::handle(ConflictException) method equals");
        assertEquals(httpServletRequest.getRequestURI(), conflictResponseBodyDTO.getPath(),
                     "ControllerExceptionHandlerV2::handle(ConflictException) path equals");
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST,
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) status equals");

        final BadRequestResponseBodyDTO badRequestResponseBodyDTO = responseEntity.getBody();

        assertNotNull(badRequestResponseBodyDTO,
                      "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) body null");

        assertNotNull(badRequestResponseBodyDTO.getTimestamp(),
                      "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) timestamp not null");
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), badRequestResponseBodyDTO.getError(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) error equals");
        assertEquals(httpServletRequest.getMethod(), badRequestResponseBodyDTO.getMethod(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) method equals");
        assertEquals(httpServletRequest.getRequestURI(), badRequestResponseBodyDTO.getPath(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) path equals");

        assertEquals(1, badRequestResponseBodyDTO.getValidationErrors().size(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatch) validationErrors size equals");
        assertEquals(e.getPropertyName(), badRequestResponseBodyDTO.getValidationErrors().get(0).getProperty(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentType) validationError property equals");
        assertEquals(e.getMessage(), badRequestResponseBodyDTO.getValidationErrors().get(0).getError(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatch) validationError error equals");

        when(e.getCause()).thenReturn(new NumberFormatException());

        responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertNotNull(responseEntity.getBody(),
                      "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) NFE body null");
        assertEquals("is invalid", responseEntity.getBody().getValidationErrors().get(0).getError(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatch) NFE error equals");

        final NullPointerException nullPointerException = mock(NullPointerException.class);

        when(nullPointerException.getMessage()).thenReturn("NullPointerException");
        when(e.getCause()).thenReturn(nullPointerException);

        responseEntity = controllerExceptionHandlerV2.handle(e, httpServletRequest);

        assertNotNull(responseEntity.getBody(),
                      "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatchException) other body null");
        assertEquals(nullPointerException.getMessage(),
                     responseEntity.getBody().getValidationErrors().get(0).getError(),
                     "ControllerExceptionHandlerV2::handle(MethodArgumentTypeMismatch) other error equals");
    }

}
