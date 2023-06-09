package com.nerdware.classroomapis.Repository;

import com.nerdware.classroomapis.Entity.Subject;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes = {SubjectRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.nerdware.classroomapis.Entity"})
@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchSubjectBySubjectNameAndCompare() {
        Subject subject = new Subject();
        subject.setSubjectName("Math");
        subject.setSubjectDescription("Mathematics");
        subjectRepository.save(subject);

        Subject expected = subjectRepository.findBySubjectName("Math");

        assertThat(expected).isEqualTo(subject);
    }

    @Test
    void CheckIfSubjectIsNull () {
        String subject = "oop";
        Subject expected = subjectRepository.findBySubjectName(subject);
        assertThat(expected).isNull();
    }
}