package com.zz.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

/**
 * 异常处理器
 */
@Configuration
public class ConsumeErrorHandlerConfig {

    // 消费异常处理器
    @Bean
    public KafkaListenerErrorHandler kafkaListenerErrorHandler() {
        return new KafkaListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
                System.out.println("消费异常：" + message.getPayload());
                return null;
            }
        };
    }

}
