package org.ootb.espresso.demo.service1.configuration.contract;

import java.util.TimeZone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.ootb.espresso.demo.service1.configuration.PhoenixConfigurationApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRestPactRunner.class)
@Provider("phoenix-configuration")
@PactBroker(host = "pact.playrc.net", port = "80")
@SpringBootTest(classes = PhoenixConfigurationApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class AppConfigProviderContractTest {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    private static TimeZone timezone = TimeZone.getDefault();
    
    @BeforeClass
    public static void before() {
        log.info("before set timezone is {}", timezone);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        log.info("after set timezone is {}", TimeZone.getDefault());
    }
    
    @State({"GET /inner/phoenix/configuration/appConfigs -> 200"})
    public void getAllAppConfigs() throws Exception {

    }

    
    @AfterClass
    public static void after() {
        log.info("before clean timezone is {}", TimeZone.getDefault());
        TimeZone.setDefault(timezone);
        log.info("after clean timezone is {}", TimeZone.getDefault());
    }
    
}
