package com.sda.school.entity.schoolclass;

import com.sda.school.entity.AbstractModel;
import com.sda.school.entity.student.StudentModel;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClassModel extends AbstractModel<Long> {

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "belongingClass")
    private Set<StudentModel> students = new HashSet<>();
}
