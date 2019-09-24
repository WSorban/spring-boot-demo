package com.sda.school.entity.schoolclass;

import com.sda.school.exception.NullIdException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/class")
public class ClassController {

    private ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClassModel> add(@RequestBody ClassModel classModel) {
        ClassModel addedClassModel = classService.add(classModel);
        return ResponseEntity.ok(addedClassModel);
    }

    @GetMapping(produces = "application/json", path="/{id}")
    public ResponseEntity<ClassModel> get(@PathVariable("id") Long id) {
        Optional<ClassModel> classModel = classService.get(id);
        if(classModel.isPresent()) {
            return ResponseEntity.ok(classModel.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClassModel>> get() {
        List<ClassModel> classs = classService.get();
        if(classs.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(classs);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            classService.delete(id);
        } catch(EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity update(@RequestBody ClassModel classModel) {
        try {
            Optional<ClassModel> updatedClass = classService.update(classModel);
            if(updatedClass.isPresent()) {
                return ResponseEntity.ok(updatedClass.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(NullIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
