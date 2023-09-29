package com.thevirtugroup.postitnote.handler;

import com.thevirtugroup.postitnote.dto.response.StatusResponse;
import com.thevirtugroup.postitnote.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String EXCEPTION_INFO = "Exception with type: '%s', message: '%s'";
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Something went wrong! Please try again or contact your administrator.";

    /**
     * Handles EntityNotFoundException and returns response with according message and http status.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public StatusResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        String message = ex.getMessage();
        LOGGER.warn(message);
        return new StatusResponse(StatusResponse.Status.ERROR, message);
    }
}




