package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.dtos.TeacherRequestDTO;
import edu.hogwarts.studentadmin.dtos.TeacherResponseDTO;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import edu.hogwarts.studentadmin.services.TeacherServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {
    private final TeacherServices teacherServices;

    public TeacherController(TeacherServices teacherServices) {

        this.teacherServices = teacherServices;
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacher(@PathVariable int id) {
        Optional<TeacherResponseDTO> teacher = teacherServices.findById(id);

        return ResponseEntity.of(teacher);
    }

    @GetMapping("/teachers")
    public List<TeacherResponseDTO> getAllTeachers() {
        List<TeacherResponseDTO> teachers = teacherServices.findAll();

        return teachers;
    }

    @PostMapping("/teachers")
    public TeacherResponseDTO createTeacher(@RequestBody TeacherRequestDTO dto) {
        return teacherServices.save(dto);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable int id, @RequestBody TeacherRequestDTO dto) {
        return ResponseEntity.of(teacherServices.updateIfExists(id, dto));
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDTO> deleteTeacher(@PathVariable int id) {
        return ResponseEntity.of(teacherServices.deleteById(id));
    }

}



