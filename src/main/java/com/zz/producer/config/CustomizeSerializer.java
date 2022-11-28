//package com.zz.producer.config;
//
//import org.apache.kafka.common.serialization.Serializer;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * 自定义序列化器
// */
//@Component
//public class CustomizeSerializer implements Serializer<String> {
//
//    @Override
//    public void configure(Map map, boolean isKey) {
//
//    }
//
//    @Override
//    public byte[] serialize(String s, String message) {
//        return new byte[0];
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//}
