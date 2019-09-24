package com.sda.school.entity.schoolclass;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    private ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ClassModel add(ClassModel classModel) {
        if(classModel.getName() == null) {
            classModel.setName("Anonim");
        }
        return classRepository.saveAndFlush(classModel);
    }

    @Override
    public Optional<ClassModel> update(ClassModel classModel) throws NullIdException {
        if(classModel.getId() == null) {
            throw new NullIdException("The provided class ID is null!");
        }
        Optional<ClassModel> existingClassModel = classRepository.findById(classModel.getId());
        if(existingClassModel.isPresent()) {
            return Optional.of(classRepository.saveAndFlush(classModel));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ClassModel> get(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public List<ClassModel> get() {
        return classRepository.findAll();
    }

    @Override
    public void delete(Long id) throws EmptyResultDataAccessException {
        classRepository.deleteById(id);
    }
}
