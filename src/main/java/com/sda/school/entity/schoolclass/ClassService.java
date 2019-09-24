package com.sda.school.entity.schoolclass;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;

public interface ClassService {

    ClassModel add(ClassModel classModel);

    Optional<ClassModel> update(ClassModel classModel) throws NullIdException;

    Optional<ClassModel> get(Long id);

    List<ClassModel> get();

    void delete(Long id);
}
