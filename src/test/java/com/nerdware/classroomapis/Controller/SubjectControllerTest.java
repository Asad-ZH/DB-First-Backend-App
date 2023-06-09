package com.nerdware.classroomapis.Controller;

import com.nerdware.classroomapis.Entity.Subject;
import com.nerdware.classroomapis.Repository.SubjectRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubjectControllerTest {

    @Autowired
    private SubjectRepository subjectRepository;

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
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("anna", "password"));
    }

    @Test
    void get() {
        String url = baseUrl.concat("/api/subject/test");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals("Hello Subject", response.getBody());
    }

    @Test
    void registerSubject() {

        String url = baseUrl.concat("/api/subject/register-subject");

        Subject subject = new Subject("phy", "Physics");
        ResponseEntity<Subject> response = restTemplate.postForEntity(url, subject, Subject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteSubject() {

        //change the id accordingly
        long subjectId = 25;

        String url = baseUrl.concat("/api/subject/delete-subject/").concat(String.valueOf(subjectId));

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, subjectId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}