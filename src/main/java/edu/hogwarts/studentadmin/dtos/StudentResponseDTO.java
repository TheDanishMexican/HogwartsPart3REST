package edu.hogwarts.studentadmin.dtos;

import java.time.LocalDate;

public record StudentResponseDTO(
        int id,
        String firstName,
        String middleName,
        String lastName,
        String allName,
        LocalDate dateOfBirth,
        boolean prefect,
        int enrollmentYear,
        boolean graduated,
        String house
) {
}

