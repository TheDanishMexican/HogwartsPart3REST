package edu.hogwarts.studentadmin.services;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {


    private final StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findFirstByAllNameContainingIgnoreCase(String name) {
        return studentRepository.findFirstByAllNameContainingIgnoreCase(name);
    }

    public Optional<Student> deleteById(int id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return existingStudent;
    }

    public Optional<Student> updateIfExists(int id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            student.setId(id);
            studentRepository.save(student);
        }
        return existingStudent;
    }
}
