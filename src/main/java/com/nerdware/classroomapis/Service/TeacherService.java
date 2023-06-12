package com.nerdware.classroomapis.Service;

import com.nerdware.classroomapis.Entity.Student;
import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Repository.SubjectRepository;
import com.nerdware.classroomapis.Repository.TeacherRepository;
import com.nerdware.classroomapis.Exception.ApiRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public String getSubject() {
        Teacher teacher = fetchData();
        if (teacher.getSubject() == null) {
            throw new ApiRequestException("No Subjects Found");
        }
        return teacher.getSubject().getSubjectName();
    }

    public Teacher fetchData() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return teacherRepository.findByTeacherUsername(username);
    }

    public List<String> getStudents() {
        Teacher teacher = fetchData();
        List<String> students = teacher.getStudents().stream().map(Student::getStudentName).toList();
        if (students.isEmpty()) {
            throw new RuntimeException("No Students Registered With This Teacher");
        }
        return students;
    }

    public void registerSubject(String subjectName) {

        Teacher teacher = fetchData();
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        if (subject == null) {
            throw new ApiRequestException("Subject Not Found");
        }
        teacher.setSubject(subject);
        subject.setTeacher(teacher);
        teacherRepository.save(teacher);
        subjectRepository.save(subject);

    }

    public void unregisterSubject() {
        Teacher teacher = fetchData();
        if (teacher.getSubject() == null) {
            throw new ApiRequestException("No Subjects Registered");
        }
        teacher.setSubject(null);
    }
}
