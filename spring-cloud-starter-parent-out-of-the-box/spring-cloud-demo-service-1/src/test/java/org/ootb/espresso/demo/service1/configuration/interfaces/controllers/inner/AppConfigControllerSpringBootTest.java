package org.ootb.espresso.demo.service1.configuration.interfaces.controllers.inner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ootb.espresso.demo.service1.configuration.PhoenixConfigurationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = {PhoenixConfigurationApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AppConfigControllerSpringBootTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetAllAppConfigs() throws Exception {

        mockMvc.perform(get("/inner/phoenix/configuration/appConfigs"))
          .andDo(MockMvcResultHandlers.print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.code", is(200)))
          .andExpect(jsonPath("$.data.length()", is(7)))
          .andExpect(jsonPath("$.data[0].id", is(1)))
          .andExpect(jsonPath("$.data[0].appId", is(20000)))
          .andExpect(jsonPath("$.data[0].androidIncomeSwitch", equalTo(1)))
          .andExpect(jsonPath("$.data[0].activeSwitch").value(1))
          .andExpect(jsonPath("$.data[0].invitationGoldNum").value(20))
          .andExpect(jsonPath("$.data[0].intervalHour").value(24))
          .andExpect(jsonPath("$.data[0].webPayTipsCountry", equalTo("")));
        
    }

}
