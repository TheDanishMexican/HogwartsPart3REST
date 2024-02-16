package edu.hogwarts.studentadmin;

import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;

public class DataInitializer implements CommandLineRunner {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public DataInitializer() {
    }

    public DataInitializer(CourseRepository courseRepository, StudentRepository studentRepository,
                           TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void run(String...args) {

    }
}
