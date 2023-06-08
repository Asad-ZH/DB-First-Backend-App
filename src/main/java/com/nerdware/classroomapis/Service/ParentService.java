package com.nerdware.classroomapis.Service;

import com.nerdware.classroomapis.Entity.Parent;
import com.nerdware.classroomapis.Entity.Student;
import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Repository.ParentRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ParentService {

    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public List<String> getChildren() {
        Parent parent = fetchData();
        List<String> children = parent.getStudents().stream().map(Student::getStudentName).toList();
        if (children.isEmpty()) {
            throw new ObjectNotFoundException("No children found", "Parent");
        }
        return children;
    }

    public HashMap<String, List<String>> getTeachers() {
        Parent parent = fetchData();

        HashMap<String, List<String>> teacherMap = new HashMap<>(parent.getStudents().size());

        for (Student student : parent.getStudents()) {
            List<String> teacherNames = new ArrayList<>(student.getTeachers().size());

            for (Teacher teacher : student.getTeachers()) {
                teacherNames.add(teacher.getTeacherName());
            }
            teacherMap.put(student.getStudentName(), teacherNames);
        }
        if (teacherMap.isEmpty()) {
            throw new ObjectNotFoundException("No teachers found", "Parent");
        }
        return teacherMap;

    }

    public HashMap<String, List<String>> getSubjects() {
        Parent parent = fetchData();

        HashMap<String, List<String>> subjectMap = new HashMap<>(parent.getStudents().size());

        for (Student student : parent.getStudents()) {
            List<String> subjectNames = new ArrayList<>(student.getSubjects().size());

            for (Subject subject : student.getSubjects()) {
                subjectNames.add(subject.getSubjectName());
            }
            subjectMap.put(student.getStudentName(), subjectNames);
        }

        if (subjectMap.isEmpty()) {
            throw new ObjectNotFoundException("No subjects found", "Parent");
        }
        return subjectMap;
    }

    public Parent fetchData() {

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return parentRepository.findByParentUsername(username);
    }

}
