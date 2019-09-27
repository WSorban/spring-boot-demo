package com.sda.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractModel<ID_TYPE> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private ID_TYPE id;

    @Column(nullable = false, updatable = false, insertable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonIgnore
    protected Timestamp created;

    //@Column(nullable = false, updatable = false, insertable = false,
    //    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //protected Timestamp updated;

}
