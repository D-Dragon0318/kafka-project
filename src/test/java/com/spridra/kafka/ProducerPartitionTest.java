package com.spridra.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author: Spridra
 * @CreateTime: 2024-12-10 15:52
 * @Describe: 分区器测试类
 * @Version: 1.0
 */

public class ProducerPartitionTest {
    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configMap.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put( ProducerConfig.PARTITIONER_CLASS_CONFIG, KafkaPartitionerMock.class.getName());

        KafkaProducer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(configMap);
            for ( int i = 0; i < 1; i++ ) {
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", "key" + i, "value" + i);
                final Future<RecordMetadata> send = producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if ( e != null ) {
                            e.printStackTrace();
                        } else {
                            System.out.println("数据发送成功：" + record.key() + "," + record.value());
                        }
                    }
                });
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

