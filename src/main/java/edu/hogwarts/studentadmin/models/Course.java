package edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;
    @OneToMany
    private List<Student> students;

    public Course() {
    }

    public Course(int id, String subject, int schoolYear, boolean current, List<Student> students) {
        this.id = id;
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.students = students;
    }

    public Course(Course otherCourse) {
        this.id = otherCourse.getId();
        this.subject = otherCourse.getSubject();
        this.schoolYear = otherCourse.getSchoolYear();
        this.current = otherCourse.isCurrent();
        this.students = otherCourse.getStudents();
    }

    public void copyFrom(Course otherCourse) {
        this.setSubject(otherCourse.getSubject());
        this.setSchoolYear(otherCourse.getSchoolYear());
        this.setCurrent(otherCourse.isCurrent());
        this.setStudents(otherCourse.getStudents());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
