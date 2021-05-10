package org.ootb.espresso.springcloud.demo.embedded.kafka;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

//@DirtiesContext
@EmbeddedKafka(partitions = 1,
         topics = {
                 "kafkastreamstests_streaming_topic1",
                 "kafkastreamstests_streaming_topic2" },
         brokerProperties = {"log.index.interval.bytes = 4096","num.io.threads = 8"},
         //brokerPropertiesLocation = "classpath:application.properties",
         controlledShutdown = false,//用来控制在Broker意外关闭时减少此Broker上Partition的不可用时间
         count = 1,//broker的节点数量
         ports = {9692})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles({"embeddedkafka"})
public class EmbeddedKafkaTest {

/**
 * Since you are using JUnit5, don’t use the JUnit4 EmbeddedKafkaRule, use EmbeddedKafkaBrokerinstead; or simply add @EmbeddedKafkaand the broker will be added as a bean to the Spring application context and its life cycle managed by Spring (use @DirtiesContext to destroy); for non-Spring tests, the broker will be created (and destroyed) by the JUnit5 EmbeddedKafkaConditionand is available via EmbeddedKafkaCondition.getBroker().
Don’t use explicit ports; let the broker use its default random port and use embeddedKafka.getBrokersAsString() for the bootstrap servers property.
If you must manage the brokers yourself (in @BeforeAll), destroy() them in @AfterAll
 */
//    @Autowired
//    private EmbeddedKafkaBroker embeddedKafka;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final String KAFKA_LISTENER_ID_1 = "KAFKA_TEST111_LISTENER_ID_12311";
    private static final String KAFKA_LISTENER_ID_2 = "KAFKA_TEST111_LISTENER_ID_12312";

    private static final String TOPIC = "test-topic-kl";
    
    @Autowired
    private KafkaListenerEndpointRegistry registry;
    
    
    /**编程方式构建内嵌式KafkaBroker**/
//    @Bean
//    public EmbeddedKafkaBroker embeddedKafkaBroker() {
//        return new EmbeddedKafkaBroker(1, false, 2, "test-events-topic-1")
//            .brokerProperties(Collections.singletonMap(KafkaConfig.LogDirProp(), "/tmp/kafka-embedded-logs"));
////    }
//    
//    @Test
//    public void someTest() {
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", this.embeddedKafka);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
//        Consumer<Integer, String> consumer = cf.createConsumer();
//        this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, KafkaStreamsTests.STREAMING_TOPIC2);
//        ConsumerRecords<Integer, String> replies = KafkaTestUtils.getRecords(consumer);
//        assertThat(replies.count()).isGreaterThanOrEqualTo(1);
//    }
//
//    @Configuration
//    @EnableKafkaStreams
//    public static class KafkaStreamsConfiguration {
//
//        @Value("${" + EmbeddedKafkaBroker.SPRING_EMBEDDED_KAFKA_BROKERS + "}")
//        private String brokerAddresses;
//
//        @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//        public KafkaStreamsConfiguration kStreamsConfigs() {
//            Map<String, Object> props = new HashMap<>();
//            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
//            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddresses);
//            return new KafkaStreamsConfiguration(props);
//        }
//
//    }
    
    @Test
    public void testSend() throws Exception {
        for (int i = 0; i < 100; i++) {
            ListenableFuture<SendResult<String, String>> listenableFuture;
            if (kafkaTemplate.getDefaultTopic() != null && kafkaTemplate.getDefaultTopic().equals(TOPIC)) {
                listenableFuture = kafkaTemplate.sendDefault("key-111","value-111" + i);
            }
            else {
                listenableFuture = kafkaTemplate.send(TOPIC, "key-222","value-222" + i);
                System.out.println("sent " + listenableFuture.get());
            }
            Thread.sleep(1000);
            
            if (i == 50) {
                stopListener1();
            }
            if (i == 90) {
                startListener1();
            }
        }
    }
    
    @KafkaListener(id = KAFKA_LISTENER_ID_1, groupId = "testGroup1", topics = TOPIC)
    public void testReceive1(ConsumerRecord<String,String> consumerRecord) {
        System.out.println("[testGroup1] consumerRecord: " + consumerRecord);
    }
    
    @KafkaListener(id = KAFKA_LISTENER_ID_2, groupId = "testGroup2", topics = TOPIC)
    public void testReceive2(ConsumerRecord<String,String> consumerRecord) {
        System.out.println("[testGroup2] consumerRecord: " + consumerRecord);
    }
    
    public void stopListener1() {
        System.out.println("Kafkalistener stop...");
        registry.getListenerContainer(KAFKA_LISTENER_ID_1).stop();
        System.out.println("Kafkalistener stopped");
    }

    public void startListener1() {
        System.out.println("Kafkalistener start...");
        registry.getListenerContainer(KAFKA_LISTENER_ID_1).start();
        System.out.println("Kafkalistener started");
    }

}
