package org.ootb.espresso.demo.service1.configuration.interfaces.controllers.inner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;
import org.ootb.espresso.demo.service1.configuration.interfaces.controllers.inner.AppConfigController;
import org.ootb.espresso.demo.service1.configuration.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@WebMvcTest(AppConfigController.class
        /*excludeFilters = {@ComponentScan.Filter(
                type = FilterType.REGEX, pattern = "com.foo.bar.*")}*/)
public class AppConfigControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AppConfigService appConfigService;
    
    @Test
    public void testGetAllAppConfigs() throws Exception {

        when(appConfigService.getAllAppConfigs()).thenReturn(getAllAppConfigs());

        mockMvc.perform(get("/inner/phoenix/configuration/appConfigs"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.code", is(200)))
          .andExpect(jsonPath("$.data[0].id", is(155)))
          .andExpect(jsonPath("$.data[0].appId", is(20000)))
          .andExpect(jsonPath("$.data[0].androidIncomeSwitch", equalTo(1)))
          .andExpect(jsonPath("$.data[0].activeSwitch").value(0))
          .andExpect(jsonPath("$.data[0].invitationGoldNum").value(15.5))
          .andExpect(jsonPath("$.data[0].intervalHour").value(15))
          .andExpect(jsonPath("$.data[0].webPayTipsCountry", equalTo("cn,us")));
        
    }

    private List<AppConfigDto> getAllAppConfigs() {
        List<AppConfigDto> list = Lists.newArrayList();
        AppConfigDto appConfigDto = new AppConfigDto();
        appConfigDto.setId(155);
        appConfigDto.setAppId(20000);
        appConfigDto.setActiveSwitch(0);
        appConfigDto.setAndroidIncomeSwitch(1);
        appConfigDto.setInvitationGoldNum(new BigDecimal(15.5));
        appConfigDto.setIntervalHour(15);
        appConfigDto.setWebPayTipsCountry("cn,us");
        list.add(appConfigDto);
        return list;
    }
    
}