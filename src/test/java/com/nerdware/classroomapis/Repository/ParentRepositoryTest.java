package com.nerdware.classroomapis.Repository;

import com.nerdware.classroomapis.Entity.Parent;

import java.util.ArrayList;

import com.nerdware.classroomapis.Entity.Teacher;
import com.nerdware.classroomapis.Security.PasswordConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

@ContextConfiguration(classes = {ParentRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.nerdware.classroomapis.Entity"})
@DataJpaTest
@Import(PasswordConfig.class)
class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private PasswordConfig passwordConfigTest;

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchParentByUsernameAndCompare() {
        Parent parent = new Parent();
        parent.setParentUsername("anna");
        parent.setParentPassword(passwordConfigTest.passwordEncoder().encode("password"));
        parent.setParentRole("PARENT");
        parent.setParentName("Jane Doe");
        parent.setParentPhone(1234567890);
        parentRepository.save(parent);

        Parent expected = parentRepository.findByParentUsername("anna");

        assertThat(expected).isEqualTo(parent);
    }

    @Test
    void CheckIfParentIsNull () {
        String username = "linda";
        Parent expected = parentRepository.findByParentUsername(username);
        assertThat(expected).isNull();
    }
}