package edu.hogwarts.studentadmin.models;

import java.time.LocalDate;

public class Teacher {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private House house;
    private boolean headOfHouse;
    private EmploymentType employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;
}
