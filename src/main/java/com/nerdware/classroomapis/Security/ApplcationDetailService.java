package com.nerdware.classroomapis.Security;


import com.nerdware.classroomapis.Entity.Parent;
import com.nerdware.classroomapis.Entity.Student;
import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Repository.ParentRepository;
import com.nerdware.classroomapis.Repository.StudentRepository;
import com.nerdware.classroomapis.Repository.TeacherRepository;
import com.nerdware.classroomapis.Service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApplcationDetailService implements UserDetailsService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public ApplcationDetailService(ParentRepository personRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.parentRepository = personRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = null, name = null, password = null;


        if (teacherRepository.findByTeacherUsername(username) != null) {
            Teacher user = teacherRepository.findByTeacherUsername(username);
            role = user.getTeacherRole();
            name = user.getTeacherName();
            password = user.getTeacherPassword();
        }
        else if (parentRepository.findByParentUsername(username) != null) {
            Parent user = parentRepository.findByParentUsername(username);
            role = user.getParentRole();
            name = user.getParentName();
            password = user.getParentPassword();
        }
        else if (studentRepository.findByStudentUsername(username) != null) {
            Student user = studentRepository.findByStudentUsername(username);
            role = user.getStudentRole();
            name = user.getStudentName();
            password = user.getStudentPassword();
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(
                username,
                password,
                Collections.singleton(new SimpleGrantedAuthority(role))
        );
    }
}
