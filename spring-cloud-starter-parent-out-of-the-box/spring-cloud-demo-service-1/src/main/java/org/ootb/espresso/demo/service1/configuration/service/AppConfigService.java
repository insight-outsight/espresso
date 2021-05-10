package org.ootb.espresso.demo.service1.configuration.service;

import java.util.List;

import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;

public interface AppConfigService {

    List<AppConfigDto> getAllAppConfigs();

}
