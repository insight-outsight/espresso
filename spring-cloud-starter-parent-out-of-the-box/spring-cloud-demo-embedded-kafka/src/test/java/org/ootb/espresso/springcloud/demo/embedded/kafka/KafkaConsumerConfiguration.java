package org.ootb.espresso.springcloud.demo.embedded.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-embeddedkafka.properties")
@ConfigurationProperties("kafka.consumer")
public class KafkaConsumerConfiguration {

    private BootstrapConfiguration bootstrap;
    private ConsumerKeyConfiguration key;
    private ConsumerValueConfiguration value;

    public BootstrapConfiguration getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(BootstrapConfiguration bootstrap) {
        this.bootstrap = bootstrap;
    }

    public ConsumerKeyConfiguration getKey() {
        return key;
    }

    public void setKey(ConsumerKeyConfiguration key) {
        this.key = key;
    }

    public ConsumerValueConfiguration getValue() {
        return value;
    }

    public void setValue(ConsumerValueConfiguration value) {
        this.value = value;
    }

    public static class ConsumerKeyConfiguration {
        private String deserializer;

        public String getDeserializer() {
            return deserializer;
        }

        public void setDeserializer(String deserializer) {
            this.deserializer = deserializer;
        }
    }

    public static class ConsumerValueConfiguration {
        private String deserializer;

        public String getDeserializer() {
            return deserializer;
        }

        public void setDeserializer(String deserializer) {
            this.deserializer = deserializer;
        }
    }

}
