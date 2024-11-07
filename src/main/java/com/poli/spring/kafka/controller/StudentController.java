package com.poli.spring.kafka.controller;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poli.spring.data.jpa.entity.Student;
import com.poli.spring.data.jpa.repository.StudentRepository;
import com.poli.spring.kafka.producer.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentController(StudentRepository studentRepository, KafkaProducer kafkaProducer, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/publish")
    public Student getStudentAndPublish(@PathVariable Long id) throws JsonProcessingException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        String studentJson = objectMapper.writeValueAsString(student);
        kafkaProducer.sendMessage(String.valueOf(student.getId()), student);

        return student;
    }
}

