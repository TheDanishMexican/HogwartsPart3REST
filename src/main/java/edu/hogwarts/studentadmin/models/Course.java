package edu.hogwarts.studentadmin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;

    public Course() {
    }

    public Course(int id, String subject, int schoolYear, boolean current) {
        this.id = id;
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
    }

    public Course(Course otherCourse) {
        this.id = otherCourse.getId();
        this.subject = otherCourse.getSubject();
        this.schoolYear = otherCourse.getSchoolYear();
        this.current = otherCourse.isCurrent();
    }

    public void copyFrom(Course otherCourse) {
        this.setSubject(otherCourse.getSubject());
        this.setSchoolYear(otherCourse.getSchoolYear());
        this.setCurrent(otherCourse.isCurrent());
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
}
