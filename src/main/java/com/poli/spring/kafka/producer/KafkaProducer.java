package com.poli.spring.kafka.producer;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.poli.spring.data.jpa.entity.Student;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Student> kafkaTemplate;

    @Value("${spring.kafka.topic.student-topic}")
    private String studentTopic;

    public KafkaProducer(KafkaTemplate<String, Student> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key, Student student) {
        kafkaTemplate.send(studentTopic, key, student);
    }
}

