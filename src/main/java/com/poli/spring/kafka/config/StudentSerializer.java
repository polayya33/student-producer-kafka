package com.poli.spring.kafka.config;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.spring.data.jpa.entity.Student;

public class StudentSerializer implements Serializer<Student> {
    @Override
    public byte[] serialize(String topic, Student student) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(student);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize student", e);
        }
    }
}
