package org.d11.boot.interfaces.rest.v2.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.d11.boot.api.v2.model.D11ApiErrorDTO;
import org.d11.boot.interfaces.rest.RefreshTokenCookieBuilder;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

/**
 * Handles exceptions and creates error responses.
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandlerV2 {

    /**
     * Handles a Not Found exception.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final NotFoundException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(UUID.randomUUID())
            .error(httpStatus.getReasonPhrase())
            .message(e.getMessage())
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
    }

    /**
     * Handles a MethodArgumentTypeMismatch exception. This is thrown when something is wrong with the request, like the
     * id parameter containing something that's not a number.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final MethodArgumentTypeMismatchException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final String parameter = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(e.getName()), ' ')
            .toLowerCase(Locale.getDefault());

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(UUID.randomUUID())
            .error(httpStatus.getReasonPhrase())
            .message("Invalid " + parameter + ": " + e.getValue())
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
    }

    /**
     * Handles a MethodArgumentNotValidException exception. This is thrown when a provided request body is not valid.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH",
                        justification = "MethodArgumentNotValidException will actually always have a body")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final MethodArgumentNotValidException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final String message = e.getBody().getDetail() != null
                ? e.getBody().getDetail().replace(".", "")
                : "Bad Request";

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(UUID.randomUUID())
            .error(httpStatus.getReasonPhrase())
            .message(message)
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
    }

    /**
     * Handles an BadCredentialsException. This is thrown when login fails.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final BadCredentialsException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        // Set a cookie with max age 0 to remove the refresh token cookie when we provide bad credentials.
        final ResponseCookie responseCookie = new RefreshTokenCookieBuilder().build();

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(UUID.randomUUID())
            .error(httpStatus.getReasonPhrase())
            .message(e.getMessage())
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());

        return ResponseEntity.status(httpStatus)
            .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
            .body(D11ApiErrorDTO);
    }

    /**
     * Handles an AccessDeniedException. This is thrown when controller method security does not allow a request.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final AccessDeniedException e,
                                                 @NonNull final HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        // If the header is null then we're sending an empty JWT or no bearer header at all.
        final HttpStatus httpStatus = authorizationHeader == null ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN;
        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(UUID.randomUUID())
            .error(httpStatus.getReasonPhrase())
            .message(e.getMessage())
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
    }

    /**
     * Handles MissingRequestCookieException. This is most likely thrown when trying to call authorize without having a
     * refresh token cookie.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<?> handle(@NonNull final MissingRequestCookieException e,
                                    @NonNull final HttpServletRequest request) {
        if (RefreshToken.COOKIE_NAME.equals(e.getCookieName())) {
            final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
            final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
                .uuid(UUID.randomUUID())
                .error(httpStatus.getReasonPhrase())
                .message("Refresh token cookie not present in request")
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI());
            return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
        }
        return handle((Exception) e, request);
    }

    /**
     * Handles all exceptions that are not being handled in other methods.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final Exception e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final UUID uuid = UUID.randomUUID();

        final String message = String.format("%s: %s", uuid, httpStatus.getReasonPhrase());
        LOGGER.error(message, e);

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
            .uuid(uuid)
            .error(httpStatus.getReasonPhrase())
            .message(e.getMessage())
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
    }

}