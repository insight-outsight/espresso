package org.ootb.espresso.springcloud.demo.embedded.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-embeddedkafka.properties")
@ConfigurationProperties("kafka.producer")
public class KafkaProducerConfiguration {

    private BootstrapConfiguration bootstrap;
    private ProducerKeyConfiguration key;
    private ProducerValueConfiguration value;

    public BootstrapConfiguration getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(BootstrapConfiguration bootstrap) {
        this.bootstrap = bootstrap;
    }

    public ProducerKeyConfiguration getKey() {
        return key;
    }

    public void setKey(ProducerKeyConfiguration key) {
        this.key = key;
    }

    public ProducerValueConfiguration getValue() {
        return value;
    }

    public void setValue(ProducerValueConfiguration value) {
        this.value = value;
    }

    public static class ProducerKeyConfiguration {
        private String serializer;

        public String getSerializer() {
            return serializer;
        }

        public void setSerializer(String serializer) {
            this.serializer = serializer;
        }
    }

    public static class ProducerValueConfiguration {
        private String serializer;

        public String getSerializer() {
            return serializer;
        }

        public void setSerializer(String serializer) {
            this.serializer = serializer;
        }
    }

}
