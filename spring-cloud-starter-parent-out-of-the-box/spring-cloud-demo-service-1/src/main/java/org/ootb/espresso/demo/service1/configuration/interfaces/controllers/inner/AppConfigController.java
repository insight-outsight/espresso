package org.ootb.espresso.demo.service1.configuration.interfaces.controllers.inner;

import java.util.List;

import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;
import org.ootb.espresso.demo.service1.configuration.service.AppConfigService;
import org.ootb.espresso.springcloud.infrastructure.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("inner/phoenix/configuration/appConfigs")
@Slf4j
public class AppConfigController {

    @Autowired
    private AppConfigService appConfigService;

    @GetMapping(value = "")
    public Response<List<AppConfigDto>> getAllAppConfigs() {
        log.debug("AppConfigController.getAllAppConfigs()");
        return Response.ok(appConfigService.getAllAppConfigs());
    }

}