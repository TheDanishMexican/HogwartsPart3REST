package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        return ResponseEntity.of(teacher);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers;
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
