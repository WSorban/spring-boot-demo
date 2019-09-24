package com.sda.school.entity.schoolclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassModel, Long> {

    ClassModel findByName(String name);
}
