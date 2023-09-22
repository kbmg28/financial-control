package br.com.kbmg.financialcontrol.config;

import br.com.kbmg.financialcontrol.dto.ResponseErrorDto;
import br.com.kbmg.financialcontrol.exceptions.AuthorizationException;
import br.com.kbmg.financialcontrol.exceptions.BadUserInfoException;
import br.com.kbmg.financialcontrol.exceptions.FinancialControlException;
import br.com.kbmg.financialcontrol.exceptions.ForbiddenException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseErrorDto> handleInternal(final Exception ex, final WebRequest request) {
        return generatedError("unexpected.error", HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ResponseErrorDto> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {
        return generatedError("forbidden.403", HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ResponseErrorDto> handleForbiddenException(final ForbiddenException ex, final WebRequest request) {
        return generatedError("forbidden.403", HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler({DataAccessException.class, HibernateException.class, DataIntegrityViolationException.class, EntityNotFoundException.class})
    protected ResponseEntity<ResponseErrorDto> handleConflict(final DataAccessException ex, final WebRequest request) {
        return generatedError("process.request.409", HttpStatus.CONFLICT, ex);
    }


    @ExceptionHandler({IllegalArgumentException.class,
            EntityExistsException.class})
    public ResponseEntity<ResponseErrorDto> handleArguments(final RuntimeException ex, final WebRequest request) {
        return generatedError(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({FinancialControlException.class})
    public ResponseEntity<ResponseErrorDto> handleServiceException(final FinancialControlException ex, final WebRequest request) {
        return generatedError(ex.getMessage(), ex.getHttpStatus(), ex);
    }

    @ExceptionHandler({BadUserInfoException.class})
    public ResponseEntity<ResponseErrorDto> handleBadUserInfoException(final BadUserInfoException ex, final WebRequest request) {
        return generatedError(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity<ResponseErrorDto> handleAccessDeniedException(final AuthorizationException ex, final WebRequest request) {
        return generatedError("unauthorized.401", HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<ResponseErrorDto> handleHttpMessageConversionException(final RuntimeException ex, final WebRequest request) {
        return generatedError("invalid.arguments.400", HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatusCode status,
            final WebRequest request) {
        String msg = "Invalid data fields";
        String errors = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        ResponseErrorDto responseError = new ResponseErrorDto(HttpStatus.BAD_REQUEST, String.format("%s %s", msg, errors));


        return handleExceptionInternal(ex, responseError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseErrorDto responseError = new ResponseErrorDto(HttpStatus.BAD_REQUEST, "Invalid data");
        return handleExceptionInternal(ex, responseError, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<ResponseErrorDto> generatedError(String message, HttpStatus http, Exception ex) {
        ResponseErrorDto responseError = new ResponseErrorDto(http, message);
        log.error(message, ex);
        return new ResponseEntity<>(responseError, http);
    }

}
