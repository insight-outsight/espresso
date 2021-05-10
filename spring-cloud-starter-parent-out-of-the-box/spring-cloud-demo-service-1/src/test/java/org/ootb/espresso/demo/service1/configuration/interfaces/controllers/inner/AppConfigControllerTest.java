package org.ootb.espresso.demo.service1.configuration.interfaces.controllers.inner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.ootb.espresso.demo.service1.configuration.PhoenixConfigurationApplication;
import org.ootb.espresso.demo.service1.configuration.api.models.dto.AppConfigDto;
import org.ootb.espresso.demo.service1.configuration.enums.ServerErrorEnum;
import org.ootb.espresso.demo.service1.configuration.exceptions.PhoenixConfigurationBusinessException;
import org.ootb.espresso.demo.service1.configuration.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = {PhoenixConfigurationApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AppConfigControllerTest {

    @MockBean
    private AppConfigService appConfigService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void shouldGetAllAppConfigsSuccess() throws Exception {
        
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

    @Test
    public void shouldGetAllAppConfigsFailureWithErrorCode() throws Exception {
        
        ServerErrorEnum systemError = ServerErrorEnum.SYSTEM_ERROR;
        when(appConfigService.getAllAppConfigs()).thenThrow(new PhoenixConfigurationBusinessException(systemError));
        mockMvc.perform(get("/inner/phoenix/configuration/appConfigs"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.code", is(systemError.getCode())))
          .andExpect(jsonPath("$.message", equalTo(systemError.getDesciption())));
        
    }

    @Test
    public void shouldGetAllAppConfigsFailure400() throws Exception {
        
        when(appConfigService.getAllAppConfigs()).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/inner/phoenix/configuration/appConfigs"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(jsonPath("$.message", startsWith(HttpStatus.BAD_REQUEST.getReasonPhrase())));
        
    }
    
    @Test
    public void shouldGetAllAppConfigsFailure500() throws Exception {
        
        when(appConfigService.getAllAppConfigs()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/inner/phoenix/configuration/appConfigs"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(status().isInternalServerError())
          .andExpect(jsonPath("$.code", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
          .andExpect(jsonPath("$.message", startsWith(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())));
        
    }

}
