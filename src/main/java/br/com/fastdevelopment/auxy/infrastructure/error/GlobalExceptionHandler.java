package br.com.fastdevelopment.auxy.infrastructure.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ProblemDetail createProblemDetail(
            HttpStatus status,
            String title,
            String detail
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                status,
                detail
        );

        problemDetail.setTitle(title);
        problemDetail.setProperty("timestamp", OffsetDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(
            ResourceNotFoundException exception
    ) {
        return createProblemDetail(
                HttpStatus.NOT_FOUND,
                "Resource not found",
                exception.getMessage()
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicateResource(
            DuplicateResourceException exception
    ) {
        return createProblemDetail(
                HttpStatus.CONFLICT,
                "Duplicate resource",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MissingTenantException.class)
    public ProblemDetail handleMissingTenant(
            MissingTenantException exception
    ) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Missing tenant context",
                exception.getMessage()
        );
    }

    @ExceptionHandler(InvalidTenantException.class)
    public ProblemDetail handleInvalidTenant(
            InvalidTenantException exception
    ) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid tenant context",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(
            MethodArgumentNotValidException exception
    ) {
        ProblemDetail problemDetail = createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                "One or more fields are invalid"
        );

        Map<String, String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (first, second) -> first
                ));

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleMalformedJson(
            HttpMessageNotReadableException exception
    ) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Malformed request",
                "The request body is invalid"
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(
            MethodArgumentTypeMismatchException exception
    ) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid parameter",
                "One or more request parameters have an invalid format"
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(
            DataIntegrityViolationException exception
    ) {
        return createProblemDetail(
                HttpStatus.CONFLICT,
                "Data conflict",
                "The operation violates a data constraint"
        );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedError(
            Exception exception
    ) {
        log.error("Unexpected application error", exception);

        return createProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                "An unexpected error occurred"
        );
    }
}
