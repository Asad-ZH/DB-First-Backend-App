package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Repository.StudentRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

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
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("anna", "password"));
    }

    @Test
    void get() {
        String url = baseUrl.concat("/api/student/test");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals("Hello Student", response.getBody());
    }

    @Test
    void getTheListOfSubjects() {
        String url = baseUrl.concat("/api/student/subjects");

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTheListOfTeachers() {
        String url = baseUrl.concat("/api/student/teachers");

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}