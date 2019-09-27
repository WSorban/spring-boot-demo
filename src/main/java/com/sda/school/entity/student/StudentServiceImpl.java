package com.sda.school.entity.student;

import com.sda.school.entity.AbstractService;
import com.sda.school.entity.schoolclass.ClassModel;
import com.sda.school.entity.schoolclass.ClassRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends AbstractService<Long, StudentModel, StudentRepository> {

    private ClassRepository classRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository) {
        super(studentRepository);
        this.classRepository = classRepository;
    }

    public StudentModel add(Long classId, StudentModel studentModel) throws Exception {

        Optional<ClassModel> belongingClass = classRepository.findById(classId);

        if(belongingClass.isPresent()) {
            if(studentModel.getName() == null) {
                studentModel.setName("Anonim");
            }

            studentModel.setBelongingClass(belongingClass.get());
            return repository.saveAndFlush(studentModel);
        } else {
            throw new Exception("The requested class does not exist!");
        }
    }
}
