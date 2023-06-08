package com.nerdware.classroomapis;

import com.nerdware.classroomapis.Entity.Parent;
import com.nerdware.classroomapis.Entity.Student;
import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Repository.ParentRepository;
import com.nerdware.classroomapis.Repository.StudentRepository;
import com.nerdware.classroomapis.Repository.SubjectRepository;
import com.nerdware.classroomapis.Repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ClassroomApIs2Application {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomApIs2Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ParentRepository parentRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        return args -> {

            Parent parent = new Parent();
            parent.setParentUsername("parent1");
            parent.setParentPassword("password");
            parent.setParentRole("ROLE_PARENT");
            parent.setParentName("John Doe");
            parent.setParentAddress("123 Main St");
            parent.setParentPhone(1234567890);
            parentRepository.save(parent);

            Student student = new Student();
            student.setStudentUsername("student1");
            student.setStudentPassword("password");
            student.setStudentRole("ROLE_STUDENT");
            student.setStudentName("Jane Doe");
            student.setStudentPhone(1234567890);
            student.setParent(parent);
            studentRepository.save(student);

            Teacher teacher = new Teacher();
            teacher.setTeacherUsername("tom");
            teacher.setTeacherPassword("password");
            teacher.setTeacherRole("TEACHER");
            teacher.setTeacherName("Tom");
            teacherRepository.save(teacher);

            Subject subject = new Subject();
            subject.setSubjectName("Math");
            subject.setSubjectDescription("Mathematics");
            subject.setTeacher(teacher);
            subjectRepository.save(subject);

            student.setSubjects(List.of(subject));
            subject.setStudents(List.of(student));

            teacher.setStudents(List.of(student));
            student.setTeachers(List.of(teacher));

            studentRepository.save(student);
            subjectRepository.save(subject);
            teacherRepository.save(teacher);
        };
    }

}
