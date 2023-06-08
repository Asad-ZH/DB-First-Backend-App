package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Service.SubjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/test")
    public String get() {
        return "Hello Subject";
    }

    @PostMapping("/register-subject")
    public void registerSubject(@RequestBody Subject subject) {
        subjectService.registerSubject(subject);
    }

    @PutMapping("/update-subject")
    public void updateSubject(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);
    }

    @DeleteMapping("/delete-subject")
    public void deleteSubject(@RequestParam Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
