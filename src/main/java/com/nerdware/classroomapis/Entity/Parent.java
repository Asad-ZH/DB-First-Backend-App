package com.nerdware.classroomapis.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parent")
@Data
public class Parent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_username")
    private String parentUsername;

    @Column(name = "parent_password")
    private String parentPassword;

    @Column(name = "parent_role")
    private String parentRole;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_address")
    private String parentAddress;

    @Column(name = "parent_phone")
    private int parentPhone;

    @OneToMany(mappedBy = "parent")
    private List<Student> students;


}
