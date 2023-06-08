package com.nerdware.classroomapis.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_username")
    private String teacherUsername;

    @Column(name = "teacher_password")
    private String teacherPassword;

    @Column(name = "teacher_role")
    private String teacherRole;

    @Column(name = "teacher_name")
    private String teacherName;

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @OneToOne(mappedBy = "teacher", cascade = CascadeType.MERGE)
    private Subject subject;

}
