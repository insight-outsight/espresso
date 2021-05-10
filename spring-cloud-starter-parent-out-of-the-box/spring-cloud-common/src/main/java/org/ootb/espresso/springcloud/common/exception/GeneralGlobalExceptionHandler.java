package org.ootb.espresso.springcloud.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.ootb.espresso.springcloud.infrastructure.Response;
import org.ootb.espresso.springcloud.infrastructure.exception.OotbBusinessException;
import org.ootb.espresso.springcloud.infrastructure.exception.OotbFatalBusinessException;
import org.ootb.espresso.springcloud.infrastructure.exception.OotbResourceNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public abstract class GeneralGlobalExceptionHandler {

    protected abstract Logger getLogger();

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response<String> defaultErrorHandler(final HttpServletRequest req, final Exception e) {
        return responseInternalServerErrorFailedResponse(req, e);
    }

    private Response<String> responseInternalServerErrorFailedResponse(final HttpServletRequest req,
                                                                       final Exception e) {
        String url = getRequestUrlWithQueryString(req);
        String rid = RandomStringUtils.randomNumeric(10);
        printErrorLog(rid, url, e);
        return buildFailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, rid);
    }

    protected String getRequestUrlWithQueryString(final HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        if (req.getQueryString() != null) {
            url += "?" + req.getQueryString();
        }
        return url;
    }

    protected void printErrorLog(String rid, String url, final Exception e) {
        getLogger().error("rid:{}, RequestURL:{}", rid, url, e);
    }
    
    protected void printWarnLog(String rid, String url, final Exception e) {
        getLogger().warn("rid:{}, RequestURL:{}", rid, url, e);
    }

    protected Response<String> buildFailedResponse(HttpStatus httpStatus, String rid) {
        return Response.failed(httpStatus, httpStatus.getReasonPhrase() + ", rid:" + rid);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> illegalArgumentExceptionHandler(final HttpServletRequest req, final IllegalArgumentException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    private Response<String> responseBadRequestFailedResponse(final HttpServletRequest req, final Exception e) {
        String url = getRequestUrlWithQueryString(req);
        String rid = RandomStringUtils.randomNumeric(10);
        printWarnLog(rid, url, e);
        return buildFailedResponse(HttpStatus.BAD_REQUEST, rid);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> methodArgumentNotValidExceptionHandler(final HttpServletRequest req,
                                                                   MethodArgumentNotValidException e) {
        String url = getRequestUrlWithQueryString(req);
      
        String requestURI = req.getRequestURI();

        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            errorMessage.append("[").append(error.getDefaultMessage()).append("] ");
        }

        getLogger().error("Invalid Request Param:{}", errorMessage);
        String rid = RandomStringUtils.randomNumeric(10);
        printWarnLog(rid, url, e);
        if (requestURI.startsWith("/inner")) {
            return buildFailedResponseWithMsg(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }
        return buildFailedResponse(HttpStatus.BAD_REQUEST, rid);
    }

    private Response<String> buildFailedResponseWithMsg(HttpStatus httpStatus,  String msg) {

        return Response.failed(httpStatus,  msg);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> missingServletRequestParameterExceptionHandler(final HttpServletRequest req,
                                                                           final MissingServletRequestParameterException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> httpMessageNotReadableExceptionExceptionHandler(final HttpServletRequest req,
                                                                            final HttpMessageNotReadableException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    @ExceptionHandler(value = TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> typeMismatchExceptionExceptionHandler(final HttpServletRequest req,
                                                                  final TypeMismatchException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> httpMediaTypeNotAcceptableExceptionHandler(final HttpServletRequest req,
                                                                       final HttpMediaTypeNotAcceptableException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response<String> httpRequestMethodNotSupportedExceptionHandler(final HttpServletRequest req,
                                                                          final HttpRequestMethodNotSupportedException e) {
        return responseBadRequestFailedResponse(req, e);
    }

    @ExceptionHandler(value = OotbResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response<String> athenaResourceNotFoundExceptionHandler(final HttpServletRequest req, final OotbResourceNotFoundException e) {
        String url = getRequestUrlWithQueryString(req);
        String rid = RandomStringUtils.randomNumeric(10);
        printErrorLog(rid, url, e);
        return buildFailedResponse(HttpStatus.NOT_FOUND, rid);
    }

    @ExceptionHandler(value = OotbBusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<String> athenaBusinessExceptionHandler(final HttpServletRequest req, final OotbBusinessException e) {
        return Response.failed(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = OotbFatalBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response<String> athenaFatalBusinessExceptioHandler(final HttpServletRequest req,
                                                               final OotbFatalBusinessException e) {
        return responseInternalServerErrorFailedResponse(req, e);
    }

}
