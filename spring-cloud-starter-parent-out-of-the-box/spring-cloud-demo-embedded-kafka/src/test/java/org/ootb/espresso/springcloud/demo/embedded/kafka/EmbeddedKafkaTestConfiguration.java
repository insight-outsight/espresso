package org.ootb.espresso.springcloud.demo.embedded.kafka;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class EmbeddedKafkaTestConfiguration {

    private static String TOPIC = "test-topic-kl";
    
    @Autowired
    private KafkaProducerConfiguration producerConfig;
    @Autowired
    private KafkaConsumerConfiguration consumerConfig;
    
    public Map<String, Object> producerProperties() {
        Map<String, Object> props = new HashMap<>();
        //Kafka服务地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfig.getBootstrap().getServers());
//        //设置当前客户端id
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getProperty("kafka.producer.client.id"));
//        //设置消费端确认机制
//        props.put(ProducerConfig.ACKS_CONFIG, properties.getProperty("kafka.producer.acks"));
//        //发送失败重试次数
//        props.put(ProducerConfig.RETRIES_CONFIG, properties.getProperty("kafka.producer.retries"));
//        //批处理条数,当多个记录被发送至统一分区时，producer对于同一个分区来说，会按照 batch.size 的大小进行统一收集，批量发送
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getProperty("kafka.producer.batch.size"));
//        //与 batch.size 配合使用。延迟统一收集，产生聚合，然后批量发送至broker
//        props.put(ProducerConfig.LINGER_MS_CONFIG,properties.getProperty("kafka.producer.linger.ms"));
//        //Key序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerConfig.getKey().getSerializer());
//        //Value序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerConfig.getValue().getSerializer());
 
        return props;
    }
    
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<String, String>(producerProperties());
    }
    
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> pf) {
        return new KafkaTemplate<>(pf);
    }
    
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
 
        //Kafka服务地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.getBootstrap().getServers());
//        //消费组
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getProperty("kafka.consumer.group.id"));
//        //设置
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, properties.getProperty("kafka.consumer.enable.auto.commit"));
//        //设置间隔时间
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, properties.getProperty("kafka.consumer.auto.commit.interval.ms"));
        //Key反序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerConfig.getKey().getDeserializer());
        //Value反序列化
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumerConfig.getValue().getDeserializer());
//        //从头开始消费
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getProperty("kafka.consumer.auto.offset.reset"));
 
        return props;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerProperties());
    }
 
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public KafkaAdmin admin(KafkaProperties properties){
        KafkaAdmin admin = new KafkaAdmin(properties.buildAdminProperties());
        //默认这个值是False的，在Broker不可用时，不影响Spring 上下文的初始化。如果你觉得Broker不可用影响正常业务需要显示的将这个值设置为True
        admin.setFatalIfBrokerNotAvailable(true);
        //默认值为True，也就是Kafka实例化后会自动创建已经实例化的NewTopic对象
//        admin.setAutoCreate(false);
        //当setAutoCreate为false时，需要我们程序显示的调用admin的initialize()方法来初始化NewTopic对象
//        admin.initialize();
        return admin;
    }
    
//    @Bean
//    public NewTopic newTopic1() {
//        return new NewTopic(TOPIC, 1, (short) 1);
//    }

}
