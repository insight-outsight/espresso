package org.ootb.espresso.demo.service1.configuration.models.generated;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntity;
import org.ootb.espresso.demo.service1.configuration.models.generated.AppConfigEntityMapper;
import org.ootb.espresso.demo.service1.configuration.service.ObjectFormat;
import org.ootb.espresso.demo.service1.configuration.service.impl.JacksonObjectFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Slf4j
public class AppConfigEntityMapperTest {

    @Autowired
    private AppConfigEntityMapper appConfigEntityMapper;
    
    private final ObjectFormat format = new JacksonObjectFormat((cause, e) -> {
        log.warn("{}", cause, e);
        return null;
    });
    
    @Test
    public void testSelectAll() {
        List<AppConfigEntity> selectAll = appConfigEntityMapper.selectAll();
        assertThat(selectAll.size()).isEqualTo(7);
        format.fromJsonArray(format.toJson(selectAll), AppConfigEntity.class);

        selectAll.forEach(item -> {
            String json = format.toJson(item);
            log.info("AppConfigEntity json is , {}", json);
            AppConfigEntity fromJson = format.fromJson(json, AppConfigEntity.class);
            log.info("AppConfigEntity is , {}", fromJson);
        });
        AppConfigEntity appConfigEntity = selectAll.get(0);
        assertThat(appConfigEntity.getId()).isEqualTo(1);
        assertThat(appConfigEntity.getAndroidIncomeSwitch()).isEqualTo(1);
        assertThat(appConfigEntity.getIosIncomeSwitch()).isEqualTo(1);
        assertThat(appConfigEntity.getIosVersion()).isEqualTo(0);
        assertThat(appConfigEntity.getInvitationGoldNum()).isEqualTo(new BigDecimal(20));
        assertThat(appConfigEntity.getRecommendSwitch()).isEqualTo(0);
    }

}
