package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  // https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/web/client/TestRestTemplate.html
  private TestRestTemplate restTemplate;

  @Test
  public void getStudentsShouldReturnEmptyStudentsArray() {
    //GIVEN

    //WHEN
    ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/students", Student[].class);
    HttpStatus statusCode = response.getStatusCode();
    Student[] students = response.getBody();

    //THEN
    assertEquals(HttpStatus.OK, statusCode);
    assertEquals(0, students.length);
  }

  @Test
  public void getStudentsShouldReturnAllStudents() {
    //Post
    ResponseEntity<Student> postResponse = restTemplate.postForEntity(
        "http://localhost:" + port + "/students",
        new Student("1", "Frank", 22, "uni1"),
        Student.class);

    assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    assertEquals(new Student("1", "Frank", 22, "uni1"), postResponse.getBody());


    //WHEN
    ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/students", Student[].class);
    HttpStatus statusCode = response.getStatusCode();
    Student[] students = response.getBody();

    //THEN
    assertEquals(HttpStatus.OK, statusCode);
    assertEquals(1, students.length);
    assertEquals(new Student("1", "Frank", 22, "uni1"), students[0]);
  }

}
