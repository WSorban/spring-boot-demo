package com.sda.school.entity.student;

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
@RequestMapping("/rest/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentModel> add(@RequestBody StudentModel studentModel) {
        StudentModel addedStudentModel = studentService.add(studentModel);
        return ResponseEntity.ok(addedStudentModel);
    }

    @GetMapping(produces = "application/json", path="/{id}")
    public ResponseEntity<StudentModel> get(@PathVariable("id") Long id) {
        Optional<StudentModel> student = studentService.get(id);
        if(student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentModel>> get() {
        List<StudentModel> students = studentService.get();
        if(students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            studentService.delete(id);
        } catch(EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity update(@RequestBody StudentModel studentModel) {
        try {
            Optional<StudentModel> updatedStudent = studentService.update(studentModel);
            if(updatedStudent.isPresent()) {
                return ResponseEntity.ok(updatedStudent.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(NullIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
