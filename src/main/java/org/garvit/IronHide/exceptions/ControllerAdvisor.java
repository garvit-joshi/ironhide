package org.garvit.IronHide.exceptions;


import org.garvit.IronHide.models.APIInfo;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author garvit-joshi on 12/11/23
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static ResponseEntity<APIInfo<String>> throwBadRequest(Exception ex) {
        log.error(ex.getMessage(), ex);
        APIInfo<String> apiInfo = new APIInfo<>(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return ResponseEntity.status(apiInfo.status()).body(apiInfo);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NotNull HttpRequestMethodNotSupportedException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status,
            @NotNull WebRequest request) {
        var responseMessage = new StringBuilder();
        responseMessage.append(ex.getMethod());
        responseMessage.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods())
                .forEach(t -> responseMessage.append(t).append(" "));

        APIInfo<String> apiInfo =
                new APIInfo<>(HttpStatus.METHOD_NOT_ALLOWED, responseMessage.toString());
        return ResponseEntity.status(apiInfo.status()).body(apiInfo);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<APIInfo<String>> handleValidationException(
            ValidationException ex, WebRequest request) {
        return throwBadRequest(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIInfo<String>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        return throwBadRequest(ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<APIInfo<String>> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        return throwBadRequest(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIInfo<String>> handleAll(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        APIInfo<String> apiInfo =
                new APIInfo<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return ResponseEntity.status(apiInfo.status()).body(apiInfo);
    }
}

