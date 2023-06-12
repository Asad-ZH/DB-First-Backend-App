package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/test")
    public String get() {
        return "Hello Teacher";
    }


    @GetMapping("/subject")
    public String getSubject() {
        return teacherService.getSubject();
    }

    @GetMapping("/students")
    public List<String> getStudents() {
        return teacherService.getStudents();
    }

    @PostMapping("/register-subject/{subjectName}")
    public void registerSubject(@PathVariable String subjectName) {
        teacherService.registerSubject(subjectName);
    }

    @PostMapping("/unregister-subject")
    public void unregisterSubject() {
        teacherService.unregisterSubject();
    }
}
