package com.freedom.wishlist.config.exception;


import com.freedom.wishlist.domain.exception.FullWishlistException;
import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @ExceptionHandler(value = WishlistNotFoundException.class)
    protected ResponseEntity handleNotFoundException(final WishlistNotFoundException ex) {
        var message = getMessage((ex.getMessage()));
        final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, message);
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = FullWishlistException.class)
    protected ResponseEntity handleFullWishlistException(final FullWishlistException ex) {
        var message = getMessage((ex.getMessage()));
        final var apiError = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, message);
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity handleProductNotFoundException(final ProductNotFoundException ex) {
        var message = getMessage((ex.getMessage()));
        final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, message);
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> processUnmergeException(final MethodArgumentNotValidException ex) {

        var result = ex.getBindingResult();
        final var apiError = processFieldErrors(result.getFieldErrors());
        return new ResponseEntity(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private String getMessage(final String errorCode, final String... args) {
        return this.applicationContext.getMessage(errorCode, args, Locale.getDefault());
    }

    private ErrorResponse processFieldErrors(final List<FieldError> fieldErrors) {
        var error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        fieldErrors.forEach(fieldError -> error.addFieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                )
        );
        return error;
    }
}
