package com.nerdware.classroomapis.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_username")
    private String studentUsername;

    @Column(name = "student_password")
    private String studentPassword;

    @Column(name = "student_role")
    private String studentRole;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_phone")
    private int studentPhone;

    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

}