package edu.hogwarts.studentadmin.dtos;

import edu.hogwarts.studentadmin.models.Student;

public class StudentPatchDTO {

    private Boolean prefect;
    private Integer schoolYear;
    private Boolean graduated;
    private Integer graduationYear;

    public void applyPatch(Student student) {
        if (prefect != null) {
            student.setPrefect(prefect);
        }
        if (schoolYear != null) {
            student.setSchoolYear(schoolYear);
        }
        if (graduationYear != null) {
            student.setGraduationYear(graduationYear);
            student.setGraduated(true);
        }
    }

    public Boolean getPrefect() {
        return prefect;
    }

    public void setPrefect(Boolean prefect) {
        this.prefect = prefect;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
