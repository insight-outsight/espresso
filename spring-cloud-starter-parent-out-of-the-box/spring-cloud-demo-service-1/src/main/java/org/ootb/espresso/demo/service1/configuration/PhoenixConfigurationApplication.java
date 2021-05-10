package org.ootb.espresso.demo.service1.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ootb.espresso"})
public class PhoenixConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoenixConfigurationApplication.class, args);
	}

}
