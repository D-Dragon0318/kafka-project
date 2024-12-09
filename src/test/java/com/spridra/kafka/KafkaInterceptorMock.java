package com.spridra.kafka;

/**
 * @Author: Spridra
 * @CreateTime: 2024-12-09 17:11
 * @Describe: TODO 自定义数据拦截器
 *  *      1. 实现Kafka提供的生产者接口ProducerInterceptor
 *  *      2. 定义数据泛型 <K, V>
 *  *      3. 重写方法
 *  *         onSend
 *  *         onAcknowledgement
 *  *         close
 *  *         configure
 * @Version: 1.0
 */

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class KafkaInterceptorMock implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }
}
