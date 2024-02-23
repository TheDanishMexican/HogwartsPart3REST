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
        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setAllName(entity.getAllName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setPrefect(entity.isPrefect());
        dto.setEnrollmentYear(entity.getEnrollmentYear());
        dto.setGraduated(entity.isGraduated());
        dto.setHouse(entity.getHouse().getName());

        return dto;
    }

    private Student toEntity(StudentRequestDTO dto) {
        Student studentEntity = new Student();
        studentEntity.setId(dto.getId());
        studentEntity.setFirstName(dto.getFirstName());
        studentEntity.setMiddleName(dto.getMiddleName());
        studentEntity.setLastName(dto.getLastName());
        studentEntity.setDateOfBirth(dto.getDateOfBirth());
        studentEntity.setPrefect(dto.isPrefect());
        studentEntity.setEnrollmentYear(dto.getEnrollmentYear());
        studentEntity.setGraduated(dto.isGraduated());
        studentEntity.setAllName((dto.getFirstName() + " " + (dto.getMiddleName().isEmpty() ? "" : dto.getMiddleName() + " ") + dto.getLastName() + " ").trim());
        Optional<House> house = houseRepository.findById(dto.getHouse());
        house.ifPresent(studentEntity::setHouse);

        return studentEntity;
    }
}
