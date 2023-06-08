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


    @GetMapping("/subjects")
    public String getSubjects() {
        return teacherService.getSubjects();
    }

    @GetMapping("/students")
    public List<String> getStudents() {
        return teacherService.getStudents();
    }

    @PostMapping("/register-subject")
    public void registerSubject(@RequestParam String subjectName) {
        teacherService.registerSubject(subjectName);
    }

    @PostMapping("/unregister-subject")
    public void unregisterSubject() {
        teacherService.unregisterSubject();
    }
}
