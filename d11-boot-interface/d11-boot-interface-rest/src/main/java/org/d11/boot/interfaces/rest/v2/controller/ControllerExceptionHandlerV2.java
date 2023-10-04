package org.d11.boot.interfaces.rest.v2.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.d11.boot.api.v2.model.D11ApiErrorDTO;
import org.d11.boot.interfaces.rest.RefreshTokenCookieBuilder;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
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
     * Handles a Bad Request exception. This is thrown when a request has an input that lets it get past the other Bad
     * Request validations but is still invalid in some way.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final BadRequestException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

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
     * Handles a Conflict exception. This is thrown when a request cannot be performed because the input conflicts with
     * the current state of the application.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final ConflictException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.CONFLICT;

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
     * Handles UnauthorizedException. This is thrown internally when a request lacks valid authentication credentials
     * for the requested resource.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handle(@NonNull final UnauthorizedException e,
                                    @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

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
     * Handles ForbiddenException. This is thrown internally when the server understands the request but a service
     * refuses to authorize it.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handle(@NonNull final ForbiddenException e,
                                    @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
                .uuid(UUID.randomUUID())
                .error(httpStatus.getReasonPhrase())
                .message("Access Denied")
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
     * Handles HttpRequestMethodNotSupportedException. This is most likely thrown when trying to call a method with some
     * path parameter missing.
     *
     * @param e       The exception that will be handled.
     * @param request The request that caused the exception.
     * @return Response entity with error details.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<D11ApiErrorDTO> handle(@NonNull final HttpRequestMethodNotSupportedException e,
                                                 @NonNull final HttpServletRequest request) {
        final HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        final UUID uuid = UUID.randomUUID();

        final D11ApiErrorDTO D11ApiErrorDTO = new D11ApiErrorDTO()
                .uuid(uuid)
                .error(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(D11ApiErrorDTO);
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
