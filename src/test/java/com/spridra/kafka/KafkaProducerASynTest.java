package com.spridra.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Spridra
 * @CreateTime: 2024-12-09 17:23
 * @Describe: 回调方法
 * @Version: 1.0
 */

public class KafkaProducerASynTest {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        configMap.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
        for ( int i = 0; i < 10; i++ ) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first-topic", "key-" + i, "value-" + i);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // TODO 当数据发送成功后，会回调此方法
                    System.out.println("数据发送成功：" + recordMetadata.timestamp());
                }
            });
            System.out.println("发送数据");
        }
        producer.close();
    }
}


