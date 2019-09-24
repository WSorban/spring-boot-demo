package com.sda.school.entity.student;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentModel add(Long classId, StudentModel studentModel) throws Exception;

    Optional<StudentModel> update(StudentModel studentModel) throws NullIdException;

    Optional<StudentModel> get(Long id);

    List<StudentModel> get();

    void delete(Long id);
}
