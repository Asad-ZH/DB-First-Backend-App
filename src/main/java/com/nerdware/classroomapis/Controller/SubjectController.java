package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Service.SubjectService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Subject> registerSubject(@RequestBody Subject subject) {
        return subjectService.registerSubject(subject);
    }

    @DeleteMapping("/delete-subject/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}
