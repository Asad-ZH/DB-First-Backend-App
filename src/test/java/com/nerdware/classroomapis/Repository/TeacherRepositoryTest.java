package com.nerdware.classroomapis.Repository;

import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Security.PasswordConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@EnableAutoConfiguration
@ContextConfiguration(classes = {TeacherRepository.class})
@EntityScan(basePackages = {"com.nerdware.classroomapis.Entity"})
@DataJpaTest
@Import(PasswordConfig.class)
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository underTest;

    @Autowired
    private PasswordConfig passwordEncoder;

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchTeacherByUsernameAndCompare() {

        String username = "Jane";
        Teacher teacher = new Teacher();
        teacher.setTeacherUsername("Jane");
        teacher.setTeacherPassword(passwordEncoder.passwordEncoder().encode("password"));
        teacher.setTeacherRole("TEACHER");
        teacher.setTeacherName("Jane Doe");
        underTest.save(teacher);

        Teacher expected = underTest.findByTeacherUsername(username);

        assertThat(expected).isEqualTo(teacher);
    }

    @Test
    void CheckIfTeacherIsNull () {
        String username = "Jane";
        Teacher expected = underTest.findByTeacherUsername(username);
        assertThat(expected).isNull();
    }
}