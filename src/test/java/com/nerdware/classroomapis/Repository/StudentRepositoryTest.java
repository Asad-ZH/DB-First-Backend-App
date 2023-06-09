package com.nerdware.classroomapis.Repository;

import com.nerdware.classroomapis.Entity.Student;

import com.nerdware.classroomapis.Security.PasswordConfigTest;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes = {StudentRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.nerdware.classroomapis.Entity"})
@DataJpaTest
@Import(PasswordConfigTest.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Autowired
    private PasswordConfigTest passwordEncoder;
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void fetchStudentByUsernameAndCompare() {
        String username = "anna";
        Student student = new Student();
        student.setStudentUsername("anna");
        student.setStudentPassword(passwordEncoder.passwordEncoder().encode("password"));
        student.setStudentRole("STUDENT");
        student.setStudentName("Jane Doe");
        student.setStudentPhone(1234567890);
        underTest.save(student);

        Student expected = underTest.findByStudentUsername(username);

        assertThat(expected).isEqualTo(student);
    }

    @Test
    void CheckIfStudentIsNull () {
        String username = "Jane";
        Student expected = underTest.findByStudentUsername(username);
        assertThat(expected).isNull();
    }
}