package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost:";
    private static RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        baseUrl = baseUrl.concat(String.valueOf(port));
    }

    @BeforeAll
    static void beforeAll() {
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("tom", "password"));
    }
    @Test
    void get() {
        String url = baseUrl.concat("/api/teacher/test");

        String response = restTemplate.getForObject(url, String.class);

        assertEquals("Hello Teacher", response);
    }

    @Test
    void getSubjects() {
        String url = baseUrl.concat("/api/teacher/subject");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getStudents() {
        String url = baseUrl.concat("/api/teacher/students");

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void registerSubject() {
        String subjectName = "phy";

        String url = baseUrl.concat("/api/teacher/register-subject/").concat(subjectName);

        ResponseEntity<Subject> response = restTemplate.postForEntity(url, null, Subject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void unregisterSubject() {
        String url = baseUrl.concat("/api/teacher/unregister-subject");

        ResponseEntity<Subject> response = restTemplate.postForEntity(url, null, Subject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}