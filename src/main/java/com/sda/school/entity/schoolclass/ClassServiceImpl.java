package com.sda.school.entity.schoolclass;

import com.sda.school.entity.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends AbstractService<Long, ClassModel, ClassRepository> {

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository) {
        super(classRepository);
    }

    @Override
    public ClassModel add(ClassModel classModel) {
        if(classModel.getName() == null) {
            classModel.setName("anonim");
        }
        return super.add(classModel);
    }
}
