package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.dtos.StudentPatchDTO;
import edu.hogwarts.studentadmin.dtos.StudentRequestDTO;
import edu.hogwarts.studentadmin.dtos.StudentResponseDTO;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.services.StudentServices;
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

    @GetMapping("/students/name/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String name) {
        Optional<Student> student = studentServices.findFirstByAllNameContainingIgnoreCase(name);
        System.out.println(name);
        return ResponseEntity.of(student);
    }

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentResponseDTO> students = studentServices.findAll();

        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable int id) {
        Optional<StudentResponseDTO> student = studentServices.findById(id);

        return ResponseEntity.of(student);
    }

    @PostMapping("/students")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO student) {
        return studentServices.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO student) {
        return ResponseEntity.of(studentServices.updateIfExists(id, student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable int id) {
        return ResponseEntity.of(studentServices.deleteById(id));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> patchStudent(@PathVariable int id, @RequestBody StudentPatchDTO student) {
        return ResponseEntity.of(studentServices.patchStudentIfExists(id, student));
    }




}
