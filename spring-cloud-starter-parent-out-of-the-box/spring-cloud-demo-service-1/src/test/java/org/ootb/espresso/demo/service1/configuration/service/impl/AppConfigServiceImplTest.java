package org.ootb.espresso.demo.service1.configuration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntity;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntityMapper;
import org.ootb.espresso.demo.service1.configuration.service.AppConfigService;
import org.ootb.espresso.demo.service1.configuration.service.impl.AppConfigServiceImpl;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class AppConfigServiceImplTest {

    private static Faker FAKER = new Faker(Locale.CHINA);

    @Mock
    private AppConfigEntityMapper appConfigEntityMapper;
    
    @InjectMocks
    private final AppConfigService appConfigService = new AppConfigServiceImpl();
    
    @Test
    public void testGetAllAppConfigs() {
        List<AppConfigEntity> appConfigEntityList = getAppConfigEntityList(3);
        when(appConfigEntityMapper.selectAll()).thenReturn(appConfigEntityList);

        List<AppConfigDto> allAppConfigs = appConfigService.getAllAppConfigs();
        
        assertThat(allAppConfigs.size()).isEqualTo(appConfigEntityList.size());
        for (int i = 0; i < appConfigEntityList.size(); i++) {
            AppConfigEntity appConfigEntity = appConfigEntityList.get(i);
            AppConfigDto appConfigDto = allAppConfigs.get(i);
            log.debug("getAndroidIncomeSwitch={}",appConfigDto.getAndroidIncomeSwitch());
            assertThat(appConfigDto.getAndroidIncomeSwitch()).isEqualTo(appConfigEntity.getAndroidIncomeSwitch());
            log.debug("getIosIncomeSwitch={}",appConfigDto.getIosIncomeSwitch());
            assertThat(appConfigDto.getIosIncomeSwitch()).isEqualTo(appConfigEntity.getIosIncomeSwitch());
            log.debug("getAndroidVersion={}",appConfigDto.getAndroidVersion());
            assertThat(appConfigDto.getAndroidVersion()).isEqualTo(appConfigEntity.getAndroidVersion());
            log.debug("getActiveSwitch={}",appConfigDto.getActiveSwitch());
            assertThat(appConfigDto.getActiveSwitch()).isEqualTo(appConfigEntity.getActiveSwitch());
            log.debug("getAppId={}",appConfigDto.getAppId());
            assertThat(appConfigDto.getAppId()).isEqualTo(appConfigEntity.getAppId());
            log.debug("appConfigEntity.getInvitationGoldNum={}",appConfigEntity.getInvitationGoldNum());
            log.debug("appConfigDto.getInvitationGoldNum={}",appConfigDto.getInvitationGoldNum());
            assertThat(appConfigDto.getInvitationGoldNum()).isEqualTo(appConfigEntity.getInvitationGoldNum());
            log.debug("appConfigDto.getBuryRecordSwitch={}",appConfigDto.getBuryRecordSwitch());
            assertThat(appConfigDto.getBuryRecordSwitch()).isEqualTo(appConfigEntity.getBuryRecordSwitch());
            log.debug("appConfigDto.getThirdPayRedirectUrl={}",appConfigDto.getThirdPayRedirectUrl());
            assertThat(appConfigDto.getThirdPayRedirectUrl()).isEqualTo(appConfigEntity.getThirdPayRedirectUrl());
            log.debug("appConfigDto.getCreateTime={}",appConfigDto.getCreateTime());
            assertThat(appConfigDto.getCreateTime()).isEqualTo(appConfigEntity.getCreateTime());
            log.debug("appConfigDto.getTextTranslate={}",appConfigDto.getTextTranslate());
            assertThat(appConfigDto.getTextTranslate()).isEqualTo(appConfigEntity.getTextTranslate());
            log.debug("appConfigEntity.getGoddessNotMatchTime={}",appConfigEntity.getGoddessNotMatchTime());
            log.debug("appConfigDto.getGoddessNotMatchTime={}",appConfigDto.getGoddessNotMatchTime());
            assertThat(appConfigDto.getGoddessNotMatchTime()).isEqualTo(appConfigEntity.getGoddessNotMatchTime());
        }
    }

    private List<AppConfigEntity> getAppConfigEntityList(int limit) {
        final AtomicInteger counter = new AtomicInteger(0);
        return Stream.generate(() -> generateAppConfigEntity(counter.getAndIncrement())).limit(limit).collect(Collectors.toList());
    }

    private AppConfigEntity generateAppConfigEntity(int i) {
        AppConfigEntity appConfigEntity = new AppConfigEntity();
        appConfigEntity.setAndroidIncomeSwitch(FAKER.number().numberBetween(0, 1));
        appConfigEntity.setIosIncomeSwitch(FAKER.number().numberBetween(0, 1));
        appConfigEntity.setAndroidVersion(FAKER.number().numberBetween(100, 200));
        appConfigEntity.setActiveSwitch(i);
        appConfigEntity.setAdSwitch(FAKER.number().numberBetween(0, 1));
        appConfigEntity.setAppId(FAKER.number().numberBetween(1000, 2000));
        double randomDouble = FAKER.number().randomDouble(2, 40, 50);
        System.out.println(randomDouble);
        appConfigEntity.setInvitationGoldNum(new BigDecimal("" + randomDouble));
        appConfigEntity.setBuryRecordSwitch(FAKER.number().numberBetween(0, 1));
        appConfigEntity.setThirdPayRedirectUrl("https://"+FAKER.internet().url());
        appConfigEntity.setCreateTime(FAKER.date().past(1, TimeUnit.MINUTES));
        appConfigEntity.setTextTranslate(FAKER.number().numberBetween(0, 1));
        appConfigEntity.setGoddessNotMatchTime(FAKER.number().numberBetween(20, 40));
        return appConfigEntity;
    }

}
