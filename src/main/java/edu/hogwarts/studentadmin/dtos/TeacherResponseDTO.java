package edu.hogwarts.studentadmin.dtos;

import edu.hogwarts.studentadmin.models.EmploymentType;
import edu.hogwarts.studentadmin.models.House;

import java.time.LocalDate;

public record TeacherResponseDTO(
        int id,
        String firstName,
        String middleName,
        String lastName,
        House house,
        LocalDate dateOfBirth,
        boolean headOfHouse,
        EmploymentType employmentType,
        LocalDate employmentStart,
        LocalDate employmentEnd
) {
}

