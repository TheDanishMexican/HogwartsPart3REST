package edu.hogwarts.studentadmin.services;

import edu.hogwarts.studentadmin.dtos.TeacherRequestDTO;
import edu.hogwarts.studentadmin.dtos.TeacherResponseDTO;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServices {


    private final TeacherRepository teacherRepository;
    private final HouseRepository houseRepository;


    public TeacherServices(TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }

    public List<TeacherResponseDTO> findAll() {
        return teacherRepository.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<TeacherResponseDTO> findById(int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            Teacher existingTeacher = teacher.get();
            TeacherResponseDTO dto = toDTO(existingTeacher);

            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    public TeacherResponseDTO save(TeacherRequestDTO dto) {
        return toDTO(teacherRepository.save(toEntity(dto)));
    }

    public Optional<TeacherResponseDTO> updateIfExists(int id, TeacherRequestDTO dto) {

        if(teacherRepository.existsById(id)) {
            Teacher teacher = toEntity(dto);
            teacher.setId(id);
            return Optional.of(toDTO(teacherRepository.save(teacher)));
        } else {
            return Optional.empty();
        }
    }

    public Optional<TeacherResponseDTO> deleteById(int id) {
        Optional<TeacherResponseDTO> existingTeacher = this.findById(id);
        teacherRepository.deleteById(id);
        return existingTeacher;
    }

    private Teacher toEntity(TeacherRequestDTO dto) {
        return new Teacher(
                dto.firstName(),
                dto.middleName(),
                dto.lastName(),
                dto.dateOfBirth(),
                dto.headOfHouse(),
                dto.employmentType(),
                dto.employmentStart(),
                dto.employmentEnd(),
                houseRepository.findById(dto.house()).orElse(null)
        );

    }

    private TeacherResponseDTO toDTO(Teacher teacherEntity) {
        return new TeacherResponseDTO(
                teacherEntity.getId(),
                teacherEntity.getFirstName(),
                teacherEntity.getMiddleName(),
                teacherEntity.getLastName(),
                teacherEntity.getHouse(),
                teacherEntity.getDateOfBirth(),
                teacherEntity.isHeadOfHouse(),
                teacherEntity.getEmploymentType(),
                teacherEntity.getEmploymentStart(),
                teacherEntity.getEmploymentEnd()
        );
    }
}
