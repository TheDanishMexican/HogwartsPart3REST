package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.DTOs.StudentPatchDTO;
import edu.hogwarts.studentadmin.services.StudentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable int id, @RequestBody StudentPatchDTO patchDTO) {
        Optional<Student> optionalStudent = studentServices.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.applyPatch(patchDTO);
            studentServices.save(existingStudent);
            return ResponseEntity.ok().body(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/name/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String name) {
        Optional<Student> student = studentServices.findFirstByAllNameContainingIgnoreCase(name);
        System.out.println(name);
        return ResponseEntity.of(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        List<Student> students = studentServices.findAll();

        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> student = studentServices.findById(id);

        return ResponseEntity.of(student);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentServices.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        return ResponseEntity.of(studentServices.updateIfExists(id, student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        return ResponseEntity.of(studentServices.deleteById(id));
    }


}
