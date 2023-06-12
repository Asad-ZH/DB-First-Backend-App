package com.nerdware.classroomapis.Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParentControllerTest {

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
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("linda", "password"));
    }

    @Test
    void get() {
        String url = baseUrl.concat("/api/parent/test");

        String response = restTemplate.getForObject(url, String.class);

        assertEquals("Hello Parent", response);
    }

    @Test
    void getChildren() {
        String url = baseUrl.concat("/api/parent/children");

        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTeachers() {
        String url = baseUrl.concat("/api/parent/teachers");

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getSubjects() {
        String url = baseUrl.concat("/api/parent/subjects");

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}