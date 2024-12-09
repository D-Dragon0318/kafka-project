package com.spridra.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author: Spridra
 * @CreateTime: 2024-12-09 17:19
 * @Describe: 拦截器测试类
 * @Version: 1.0
 */

public class ProducerInterceptorTest {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        configMap.put( ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, KafkaInterceptorMock.class.getName());

        KafkaProducer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(configMap);
            for ( int i = 0; i < 1; i++ ) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", "key" + i, "value" + i);
                final Future<RecordMetadata> send = producer.send(record);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            if ( producer != null ) {
                producer.close();
            }
        }

    }
}

