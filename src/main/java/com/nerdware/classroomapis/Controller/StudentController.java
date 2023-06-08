package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/test")
    public String get() {
        return "Hello Student";
    }

    @GetMapping("/subjects")
    public List<String> getSubjects() {
        return studentService.getSubjects();
    }

    @GetMapping("/teachers")
    public List<String> getTeachers() {
        return studentService.getTeachers();
    }

}
