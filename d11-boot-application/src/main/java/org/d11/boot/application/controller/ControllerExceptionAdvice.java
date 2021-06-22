package org.d11.boot.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler.
 */
@ControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * Name of the timestamp property in the error map.
     */
    private static final String TIMESTAMP = "timestamp";
    /**
     * Name of the status property in the error map.
     */
    private static final String STATUS = "status";
    /**
     * Name of the error property in the error map.
     */
    private static final String ERROR = "error";
    /**
     * Name of the message property in the error map.
     */
    private static final String MESSAGE = "message";
    /**
     * Name of the path property in the error map.
     */
    private static final String PATH = "path";

    /**
     * Formatter for the error map timestamp value.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");

    /**
     * Makes sure that we return a 401 instead of a 500 when authentication fails.
     *
     * @param authenticationException Underlying AuthenticationException.
     * @param servletWebRequest       Web request that caused the exception.
     * @return ResponseEntity with HttpStatus.UNAUTHORIZED and a standard error map.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> authenticationException(final AuthenticationException authenticationException,
                                                                       final ServletWebRequest servletWebRequest) {
        final Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put(ControllerExceptionAdvice.TIMESTAMP, ZonedDateTime.now().format(ControllerExceptionAdvice.DATE_TIME_FORMATTER));
        errorMap.put(ControllerExceptionAdvice.STATUS, HttpStatus.UNAUTHORIZED.value());
        errorMap.put(ControllerExceptionAdvice.ERROR, authenticationException.getMessage());
        errorMap.put(ControllerExceptionAdvice.MESSAGE, "");
        errorMap.put(ControllerExceptionAdvice.PATH, servletWebRequest.getRequest().getRequestURI());
        return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Makes sure that we return a 403 instead of a 500 when access is denied.
     *
     * @param accessDeniedException Underlying AccessDeniedException.
     * @param servletWebRequest     Web request that caused the exception.
     * @return ResponseEntity with HttpStatus.FORBIDDEN and a standard error map.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> accessDeniedException(final AccessDeniedException accessDeniedException,
                                                                     final ServletWebRequest servletWebRequest) {
        final Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put(ControllerExceptionAdvice.TIMESTAMP, ZonedDateTime.now().format(ControllerExceptionAdvice.DATE_TIME_FORMATTER));
        errorMap.put(ControllerExceptionAdvice.STATUS, HttpStatus.FORBIDDEN.value());
        errorMap.put(ControllerExceptionAdvice.ERROR, accessDeniedException.getMessage());
        errorMap.put(ControllerExceptionAdvice.MESSAGE, "");
        errorMap.put(ControllerExceptionAdvice.PATH, servletWebRequest.getRequest().getRequestURI());
        return new ResponseEntity<>(errorMap, HttpStatus.FORBIDDEN);
    }

}
