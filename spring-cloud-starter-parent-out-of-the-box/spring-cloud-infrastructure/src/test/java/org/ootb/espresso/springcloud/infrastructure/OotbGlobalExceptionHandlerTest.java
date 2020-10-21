package org.ootb.espresso.springcloud.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OotbGlobalExceptionHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldHandleByGlobalExceptionHandler() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/oops", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(NOT_FOUND);

        entity = restTemplate.getForEntity("/bad-request", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(BAD_REQUEST);

        entity = restTemplate.getForEntity("/unexpected", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
    }

}
