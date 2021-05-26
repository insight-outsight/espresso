package org.ootb.espresso.springcloud.infrastructure;

import java.lang.invoke.MethodHandles;

import org.ootb.espresso.springcloud.infrastructure.exception.OotbResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OopsController {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private OopsServiceImpl ooopsServiceImpl;
    
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
        logger.debug("name: {}", dateTimeVO.getName());
        logger.debug("date: {}", dateTimeVO.getDate());
        logger.debug("gender: {}", dateTimeVO.getGender());
        return dateTimeVO;
    }
    
    @GetMapping(value = "/oops-get/{appId}")
    public Response<CustomJackson2TestVO> getSomething(
            @PathVariable("appId") AppIdEnum appId,
            @RequestParam(value = "gender") GenderEnum gender) {

        logger.info("controller getSomething request, appId:{}, gender:{}",
                appId, gender);

        return Response.ok(new CustomJackson2TestVO(appId, gender));

    }

    @GetMapping(value = "/mdc/test/{appId}")
    public Response<String> mdcTest(
            @PathVariable("appId") AppIdEnum appId,
            @RequestParam(value = "gender") GenderEnum gender) {

        logger.info("controller mdc test, appId:{}, gender:{}", appId, gender);
        ooopsServiceImpl.say("hello hello");
        return Response.ok("abc");

    }
    
}
