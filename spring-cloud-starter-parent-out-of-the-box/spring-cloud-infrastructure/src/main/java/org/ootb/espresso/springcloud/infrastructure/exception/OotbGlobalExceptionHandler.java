package org.ootb.espresso.springcloud.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.invoke.MethodHandles;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order
class OotbGlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    Response<?> badRequest(IllegalArgumentException ex) {
        LOG.warn("Bad request", ex);
        return Response.failed(BAD_REQUEST, ex.getMessage(), new OotbExceptionCarrier<>(ex));
    }

    @ExceptionHandler(OotbResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    Response<?> resourceNotFoundException(OotbResourceNotFoundException ex) {
        LOG.warn("Requested resource missing", ex);
        return Response.failed(NOT_FOUND, ex.getMessage(), new OotbExceptionCarrier<>(ex));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    Response<?> unexpectedException(Exception ex) {
        LOG.error("Unexpected exception", ex);
        return Response.failed(INTERNAL_SERVER_ERROR, ex.getMessage(), new OotbExceptionCarrier<>(ex));
    }
}
