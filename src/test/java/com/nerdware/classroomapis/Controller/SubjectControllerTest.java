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
    }

    @Test
    void get() {
        String url = baseUrl.concat("/api/subject/test");

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals("Hello Subject", response.getBody());
    }

    @Test
    void registerSubject() {
        String registerUrl = baseUrl.concat("/api/subject/register-subject");

        Subject subject = new Subject("Math", "Physics");
        ResponseEntity<Subject> response = restTemplate.postForEntity(registerUrl, subject, Subject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        String deleteUrl = baseUrl.concat("/api/subject/delete-subject/").concat(String.valueOf(response.getBody().getSubjectId()));
        restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class, subject.getSubjectId());
    }

    @Test
    void deleteSubject() {
        //change the id accordingly
        long subjectId = 25;
        String registerUrl = baseUrl.concat("/api/subject/register-subject");

        Subject subject = new Subject("Math", "Physics");
        ResponseEntity<Subject> registerResponse = restTemplate.postForEntity(registerUrl, subject, Subject.class);


        String deleteUrl = baseUrl.concat("/api/subject/delete-subject/").concat(String.valueOf(registerResponse.getBody().getSubjectId()));

        ResponseEntity<String> deleteResponse = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class, subjectId);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    }
}