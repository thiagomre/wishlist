package com.freedom.wishlist.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String context;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorResponse(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(final String path, final String message) {
        final var error = new FieldError(path, message);
        fieldErrors.add(error);
    }

    static class FieldError {
        private final String field;
        private final String message;

        public FieldError(final String field, final String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
