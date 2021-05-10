package org.ootb.espresso.demo.service1.configuration.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.rcplatform.phoenix.configuration.models")
public class MybatisConfiguration {
}
