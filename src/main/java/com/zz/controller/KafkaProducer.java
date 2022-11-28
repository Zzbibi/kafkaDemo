package com.zz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息（发后即忘）
    @RequestMapping("/produceMessage1/{message}")
    public void produceMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1",  normalMessage);
    }

    // 发送消息（异步）
    @RequestMapping("/produceMessage2/{message}")
    public void produceMessage2(@PathVariable("message") String callbackMessgae) {
//        kafkaTemplate.send("topic1", callbackMessgae).addCallback(
//                new SuccessCallback<SendResult<String, Object>>() {
//                    @Override
//                    public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
//                        // 消息发送的topic
//                        String topic = stringObjectSendResult.getRecordMetadata().topic();
//                        // 消息发送的分区
//                        int partition = stringObjectSendResult.getRecordMetadata().partition();
//                        // 消息在分区内的offset
//                        long offset = stringObjectSendResult.getRecordMetadata().offset();
//
//                        System.out.println("消息发送成功：" + topic + "-" + partition + "-" + offset);
//                    }
//                },
//                new FailureCallback() {
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        System.out.println("消息发送失败：" + throwable.getMessage());
//                    }
//                }
//        );

        kafkaTemplate.send("topic1", callbackMessgae).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("消息发送失败" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                // 消息的topic
                String topic = stringObjectSendResult.getRecordMetadata().topic();
                // 消息的partition
                int partition = stringObjectSendResult.getRecordMetadata().partition();
                // 消息在分区中的offset
                long offset = stringObjectSendResult.getRecordMetadata().offset();
                System.out.println("消息发送成功：" + topic + "-" + partition + "-" + offset);
            }
        });
    }

}
