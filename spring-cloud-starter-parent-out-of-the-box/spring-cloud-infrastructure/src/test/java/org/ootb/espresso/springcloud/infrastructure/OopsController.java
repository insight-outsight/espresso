package org.ootb.espresso.springcloud.infrastructure;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OopsController {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/oops")
    public String oops() {
        throw new OotbResourceNotFoundException("oops");
    }

    @GetMapping("/bad-request")
    public String badRequest() {
        throw new IllegalArgumentException("bad-request");
    }

    @GetMapping("/unexpected")
    public String unexpected() {
        throw new RuntimeException("unexpected");
    }

    @PostMapping("/datetime")
    public CustomJackson2TestVO datetime(@RequestBody CustomJackson2TestVO dateTimeVO) {
        LOG.debug("name: {}", dateTimeVO.getName());
        LOG.debug("date: {}", dateTimeVO.getDate());
        LOG.debug("gender: {}", dateTimeVO.getGender());
        return dateTimeVO;
    }
}
