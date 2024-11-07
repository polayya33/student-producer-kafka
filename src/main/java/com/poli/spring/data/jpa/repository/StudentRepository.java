package com.poli.spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.spring.data.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
