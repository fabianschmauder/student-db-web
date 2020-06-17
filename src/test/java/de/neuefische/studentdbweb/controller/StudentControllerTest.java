package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.db.StudentDb;
import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

  @Autowired
  private StudentDb studentDb;

  @BeforeEach
  public void resetDatabase() {
    studentDb.clearDb();
  }

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
  public void putStudentShouldAddStudentToDatabase() {
    //GIVEN
    HttpEntity<Student> requestEntity = new HttpEntity<>(new Student("1", "Frank", 22, "uni1"));

    //WHEN
    ResponseEntity<Student> postResponse = restTemplate.exchange("http://localhost:" + port + "/students", HttpMethod.PUT, requestEntity, Student.class);

    //THEN
    assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    assertEquals(new Student("1", "Frank", 22, "uni1"), postResponse.getBody());
    assertTrue(studentDb.getStudents().contains(new Student("1", "Frank", 22, "uni1")));
  }

  @Test
  public void getStudentsShouldReturnAllStudents() {
    //GIVEN
    studentDb.add(new Student("1", "Frank", 22, "uni1"));
    studentDb.add(new Student("2", "Caro", 22, "uni2"));


    //WHEN
    ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/students", Student[].class);
    HttpStatus statusCode = response.getStatusCode();
    Student[] students = response.getBody();

    //THEN
    assertEquals(HttpStatus.OK, statusCode);
    assertEquals(2, students.length);
    assertEquals(new Student("1", "Frank", 22, "uni1"), students[0]);
    assertEquals(new Student("2", "Caro", 22, "uni2"), students[1]);
  }

  @Test
  public void getStudentsShouldReturnFilteredStudents() {
    //GIVEN
    studentDb.add(new Student("1", "Frank", 22, "uni1"));
    studentDb.add(new Student("2", "Caro", 22, "uni2"));
    studentDb.add(new Student("3", "Franzi", 23, "uni3"));


    //WHEN
    ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/students?query=fr", Student[].class);
    HttpStatus statusCode = response.getStatusCode();
    Student[] students = response.getBody();

    //THEN
    assertEquals(HttpStatus.OK, statusCode);
    assertEquals(2, students.length);
    assertEquals(new Student("1", "Frank", 22, "uni1"), students[0]);
    assertEquals(new Student("3", "Franzi", 23, "uni3"), students[1]);
  }

}
