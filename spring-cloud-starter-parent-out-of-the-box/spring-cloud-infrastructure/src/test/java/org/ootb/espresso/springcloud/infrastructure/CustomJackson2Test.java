package org.ootb.espresso.springcloud.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ootb.espresso.facilities.JacksonJSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomJackson2Test {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ableToHandleCustomDateTime() {
        Date date = Date.from(LocalDateTime.of(2020, 4, 16, 23, 15, 2)
                .toInstant(ZonedDateTime.now().getOffset()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);

        CustomJackson2TestVO dateTimeVO = restTemplate.postForObject("/datetime",
                new HttpEntity<>("{\n"
                        + "  \"name\": \"r1\",\n"
                        + "  \"date\": \"2020-04-16 23:15:02\",\n"
                        + "  \"gender\": 23\n"
                        + "}", headers),
                CustomJackson2TestVO.class);
        System.out.println("js===" + JacksonJSONUtils.toJSON(dateTimeVO));
        dateTimeVO = restTemplate.postForObject("/datetime",
                new HttpEntity<>("{\n"
                        + "  \"name\": \"r2\",\n"
                        + "  \"date\": \"2020-04-16 23:15:02\",\n"
                        + "  \"gender\": 230\n"
                        + "}", headers),
                CustomJackson2TestVO.class);
        System.out.println("js===" + JacksonJSONUtils.toJSON(dateTimeVO));
        assertThat(dateTimeVO.getDate()).isEqualToIgnoringMillis(date);
        
        String dateTimeVO2 = restTemplate.postForObject("/datetime",
                new HttpEntity<>("{\n"
                        + "  \"name\": \"r3\",\n"
                        + "  \"date\": \"2020-04-16 23:15:02\",\n"
                        + "  \"gender\": 45\n"
                        + "}", headers),
                String.class);
        System.out.println("do2=" + dateTimeVO2);

//
//        date = Date.from(LocalDateTime.of(2020, 4, 16, 23, 15, 2)
//                .toInstant(ZoneOffset.of("+8")));
//        dateTimeVO = restTemplate.postForObject("/datetime",
//                new HttpEntity<>("{\n"
//                        + "  \"name\": \"r2\",\n"
//                        + "  \"date\": \"2020-04-16T15:15:02.012Z\",\n"
//                        + "  \"gender\": \"MALE\"\n"
//                        + "}", headers),
//                CustomJackson2TestVO.class);
//System.out.println(JacksonJSONUtils.toJSON(dateTimeVO));
//
//        assertThat(dateTimeVO.getDate()).isEqualToIgnoringMillis(date);
    }
    
//    @Test
//    public void ableToHandleCustomDateTime2() {
//        Date date = Date.from(LocalDateTime.of(2020, 4, 16, 23, 15, 2)
//                .toInstant(ZonedDateTime.now().getOffset()));
//        
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(APPLICATION_JSON_UTF8);
//        
//        DateTimeVO dateTimeVO = restTemplate.postForObject("/datetime",
//                new HttpEntity<>("{\n"
//                        + "  \"name\": \"r1\",\n"
//                        + "  \"date\": 1603087577000\n"
//                        + "}", headers),
//                DateTimeVO.class);
//        
//        assertThat(dateTimeVO.getDate()).isEqualToIgnoringMillis(date);
//        
//        
//        date = Date.from(LocalDateTime.of(2020, 4, 16, 23, 15, 2)
//                .toInstant(ZoneOffset.of("+8")));
//        dateTimeVO = restTemplate.postForObject("/datetime",
//                new HttpEntity<>("{\n"
//                        + "  \"name\": \"r2\",\n"
//                        + "  \"date\": 1603087588000\n"
//                        + "}", headers),
//                DateTimeVO.class);
//        
//        assertThat(dateTimeVO.getDate()).isEqualToIgnoringMillis(date);
//    }
}