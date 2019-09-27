package com.sda.school.entity.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sda.school.entity.AbstractModel;
import com.sda.school.entity.schoolclass.ClassModel;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class StudentModel extends AbstractModel<Long> {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClassModel belongingClass;
}
