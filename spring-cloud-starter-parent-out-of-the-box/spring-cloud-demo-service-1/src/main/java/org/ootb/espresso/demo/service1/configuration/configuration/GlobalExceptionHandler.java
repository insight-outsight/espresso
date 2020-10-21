package org.ootb.espresso.demo.service1.configuration.configuration;


import javax.servlet.http.HttpServletRequest;

import org.ootb.espresso.demo.service1.configuration.exceptions.PhoenixConfigurationBusinessException;
import org.ootb.espresso.springcloud.infrastructure.Response;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends GeneralGlobalExceptionHandler {

    @ExceptionHandler(value = PhoenixConfigurationBusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<String> configurationBusinessExceptionHandler(final HttpServletRequest req, final PhoenixConfigurationBusinessException e) {
        return Response.failed(e.getServerErrorEnum().getCode(), e.getMessage());
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

}
