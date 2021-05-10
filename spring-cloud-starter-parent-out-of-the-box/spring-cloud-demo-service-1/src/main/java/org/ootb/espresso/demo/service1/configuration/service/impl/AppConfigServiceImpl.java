package org.ootb.espresso.demo.service1.configuration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntity;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntityMapper;
import org.ootb.espresso.demo.service1.configuration.service.AppConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    private AppConfigEntityMapper appConfigEntityMapper;
    
    @Override
    public List<AppConfigDto> getAllAppConfigs() {
        List<AppConfigEntity> appConfigEntityList = appConfigEntityMapper.selectAll();
        List<AppConfigDto> appConfigDtoList = appConfigEntityList.parallelStream()
                .map(this::convert)
                .collect(Collectors.toList());
        log.debug("appConfigDtoList:{}", appConfigDtoList);
        return appConfigDtoList;
    }

    private AppConfigDto convert(AppConfigEntity appConfigEntity) {
        AppConfigDto dto = new AppConfigDto();
        BeanUtils.copyProperties(appConfigEntity, dto);
        return dto;
    }

}
