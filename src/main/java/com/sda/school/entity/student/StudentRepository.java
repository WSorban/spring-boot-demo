package com.sda.school.entity.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    StudentModel findByName(String name);
}
