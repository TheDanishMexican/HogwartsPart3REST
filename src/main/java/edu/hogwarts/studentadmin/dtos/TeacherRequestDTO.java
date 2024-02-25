package edu.hogwarts.studentadmin.dtos;

import edu.hogwarts.studentadmin.models.EmploymentType;

import java.time.LocalDate;

public record TeacherRequestDTO(
    String firstName,
    String middleName,
    String lastName,
    String house,
    LocalDate dateOfBirth,
    boolean headOfHouse,
    EmploymentType employmentType,
    LocalDate employmentStart,
    LocalDate employmentEnd
) {
}
