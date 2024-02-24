package edu.hogwarts.studentadmin.services;

import edu.hogwarts.studentadmin.dtos.StudentRequestDTO;
import edu.hogwarts.studentadmin.dtos.StudentResponseDTO;
import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;


    public StudentServices(StudentRepository studentRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<StudentResponseDTO> findById(int id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            Student existingStudent = student.get();
            StudentResponseDTO dto = toDTO(existingStudent);

            return Optional.of(dto);
        } else {
            return Optional.empty();
        }

//        return studentRepository.findById(id).map(this::toDTO);
    }

    public StudentResponseDTO save(StudentRequestDTO student) {
        return toDTO(studentRepository.save(toEntity(student)));
    }

    public Optional<Student> findFirstByAllNameContainingIgnoreCase(String name) {
        return studentRepository.findFirstByAllNameContainingIgnoreCase(name);
    }

    public Optional<StudentResponseDTO> deleteById(int id) {
        Optional<StudentResponseDTO> existingStudent = this.findById(id);
        studentRepository.deleteById(id);
        return existingStudent;
    }

    public Optional<StudentResponseDTO> updateIfExists(int id, StudentRequestDTO student) {

        if (studentRepository.existsById(id)) {
            Student studentEntity = toEntity(student);
            studentEntity.setId(id);
            studentRepository.save(studentEntity);
            return Optional.of(toDTO(studentEntity));
        } else {

            return Optional.empty();
        }
    }

    public StudentResponseDTO toDTO(Student entity) {
        return new StudentResponseDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getAllName(),
                entity.getDateOfBirth(),
                entity.isPrefect(),
                entity.getEnrollmentYear(),
                entity.isGraduated(),
                entity.getHouse().getName()
        );
    }

    private Student toEntity(StudentRequestDTO dto) {
        if(dto.name() != null) {
            return new Student(dto.name(), houseRepository.findById(dto.house()).orElse(null));
        } else {
            return new Student(
                    dto.firstName(),
                    dto.middleName(),
                    dto.lastName(),
                    dto.dateOfBirth(),
                    dto.prefect(),
                    dto.enrollmentYear(),
                    dto.graduationYear(),
                    dto.graduated(),
                    houseRepository.findById(dto.house()).orElse(null),
                    dto.schoolYear()
            );
        }
    }



}
