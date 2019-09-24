package com.sda.school.entity.student;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentModel add(StudentModel studentModel) {
        if(studentModel.getName() == null) {
            studentModel.setName("Anonim");
        }
        return studentRepository.saveAndFlush(studentModel);
    }

    @Override
    public Optional<StudentModel> update(StudentModel studentModel) throws NullIdException {
        if(studentModel.getId() == null) {
            throw new NullIdException("The provided student ID is null!");
        }
        Optional<StudentModel> existingStudentModel = studentRepository.findById(studentModel.getId());
        if(existingStudentModel.isPresent()) {
            return Optional.of(studentRepository.saveAndFlush(studentModel));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentModel> get(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<StudentModel> get() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Long id) throws EmptyResultDataAccessException {
        studentRepository.deleteById(id);
    }
}
