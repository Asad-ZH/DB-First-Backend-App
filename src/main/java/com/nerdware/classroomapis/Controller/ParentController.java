package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Service.ParentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/parent")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/test")
    public String getParent() {
        return "Hello Parent";
    }

    @GetMapping("/children")
    public List<String> getChildren() {
        return parentService.getChildren();
    }

    @GetMapping("/teachers")
    public HashMap<String, List<String>> getTeachers() {
        return parentService.getTeachers();
    }

    @GetMapping("/subjects")
    public HashMap<String, List<String>> getSubjects() {
        return parentService.getSubjects();
    }

}