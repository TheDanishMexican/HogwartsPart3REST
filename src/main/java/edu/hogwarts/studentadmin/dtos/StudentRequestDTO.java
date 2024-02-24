package edu.hogwarts.studentadmin.dtos;

import java.time.LocalDate;

public record StudentRequestDTO(
        String firstName,
        String middleName,
        String lastName,
        String name,
        LocalDate dateOfBirth,
        boolean prefect,
        int enrollmentYear,
        boolean graduated,
        int graduationYear,
        int schoolYear,
        String house
) {
}

