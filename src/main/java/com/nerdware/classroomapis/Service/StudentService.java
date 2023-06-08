package com.nerdware.classroomapis.Service;

import com.nerdware.classroomapis.Entity.Student;
import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Exception.ApiRequestException;
import com.nerdware.classroomapis.Repository.StudentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student fetchData() {

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return studentRepository.findByStudentUsername(username);
    }

    public List<String> getSubjects() {
        Student student = fetchData();
        List<String> subjects = student.getSubjects().stream().map(Subject::getSubjectName).toList();
        if (subjects.isEmpty()) {
            throw new ApiRequestException("No Subjects Registered With This Student");
        }
        return subjects;
    }

    public List<String> getTeachers() {
        Student student = fetchData();
        List<String> teachers = student.getTeachers().stream().map(Teacher::getTeacherName).toList();
        if (teachers.isEmpty()) {
            throw new ApiRequestException("No Teachers Registered With This Student");
        }
        return teachers;
    }
}
