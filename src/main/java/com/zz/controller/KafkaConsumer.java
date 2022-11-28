package com.zz.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// 消费者监听
@Component
public class KafkaConsumer {

    @KafkaListener(id = "timingConsumer", topics = {"topic1"}, containerFactory = "delayContainerFactory")
    public void consumeMessage4(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println(consumerRecord.value());
    }

//    // 指定topic消费
//    @KafkaListener(topics = {"topic1"}, errorHandler = "kafkaListenerErrorHandler")
//    public void consumeMessage1(ConsumerRecord<?, ?> record) {
//        // 消费哪一个topic，partition的消息
//        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
//    }

//    @KafkaListener(id = "consumer1", groupId = "felix_group", topicPartitions = {
//            @TopicPartition(topic = "topic1", partitions = "0"),
//            @TopicPartition(topic = "topic2", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "4"))
//    })
//    public void consumeMessage2(ConsumerRecord<?, ?> record) {
//        String topic = record.topic();
//        int partition = record.partition();
//        long offset = record.offset();
//        Object value = record.value();
//        System.out.println("topic:" + topic + "-partition:" + partition + "-offset:" + offset + "-value:" + value);
//    }

    // 批量消费
//    @KafkaListener(id = "consumer2", groupId = "felix-group", topics = "topic1", errorHandler = "kafkaListenerErrorHandler")
//    public void consumeMessage3(List<ConsumerRecord<?, ?>> records) {
//        System.out.println("批量消费一次，records.size() = " + records.size());
//        for (ConsumerRecord<?, ?> record : records)
//            System.out.println(record.value());
//    }



}
