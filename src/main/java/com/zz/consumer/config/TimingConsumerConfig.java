package com.zz.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 */
@EnableScheduling
@Component
public class TimingConsumerConfig {

    @Resource
    private KafkaListenerEndpointRegistry registry;

    @Resource
    private ConsumerFactory consumerFactory;

    // 监听器容器工厂
    @Bean
    public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory container  = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(consumerFactory);
        // 禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }

    // 定时启动监听器
    @Scheduled(cron = "0 11 17 * * ? ")
    public void startListener() {
        System.out.println("启动监听器...");
        if (!registry.getListenerContainer("timingConsumer").isRunning())
            registry.getListenerContainer("timingConsumer").start();
    }

    // 定时停止监听器
    @Scheduled(cron = "0 12 17 * * ? ")
    public void shutDownListener() {
        System.out.println("关闭监听器");
        registry.getListenerContainer("timingConsumer").pause();
    }

}
