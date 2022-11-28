//package com.zz.producer.config;
//
//import org.apache.kafka.clients.producer.Partitioner;
//import org.apache.kafka.common.Cluster;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * 自定义分区器
// */
//@Component
//public class CustomizePartitioner implements Partitioner {
//
//    @Override
//    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
//        // 分区分配逻辑；默认会根据key的哈希值来计算分区号，key为空则以轮询的方式发往各个可用分区
//        System.out.println("自定义分区:CustomizePartitioner.partition()");
//        return 0;
//    }
//
//    @Override
//    public void close() {
//        // 默认空方法，用于在关闭分区器的时候回收一些资源
//    }
//
//    @Override
//    public void configure(Map<String, ?> map) {
//        // 获取配置信息
//    }
//}
