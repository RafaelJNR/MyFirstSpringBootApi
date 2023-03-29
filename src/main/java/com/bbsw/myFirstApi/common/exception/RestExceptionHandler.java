package com.bbsw.myFirstApi.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final String EXCEPTION_OCCURRED_MSG = "An exception has occurred. Error code {}";

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiError> handleRestClientException(RestClientException ex) {
        return getResponseEntity(ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleIllegalStateException(IllegalStateException ex) {
        return getResponseEntity(ex);
    }

    private ResponseEntity<ApiError> getResponseEntity(RuntimeException ex, String... details){
        logger.error(EXCEPTION_OCCURRED_MSG, ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)){
            status = ex.getClass().getAnnotation(ResponseStatus.class).value();
        }
        ApiError errorResponse = new ApiError(String.valueOf(status.value()), ex.getMessage());
        if (details != null){
            errorResponse.setDetails(Arrays.asList(details));
        }
        return new ResponseEntity<>(errorResponse, status);
    }

}
