package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.db.StudentDb;
import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceTest {


  @Test
  public void findStudentByUniversity() {
    //GIVEN
    StudentDb studentDb = mock(StudentDb.class);

    when(studentDb.getStudents()).thenReturn(List.of(
        new Student("12", "Frank", 12, "uni1"),
        new Student("1", "Caro", 12, "uni2"),
        new Student("10", "Marwin", 32, "uni1"),
        new Student("3", "Alpay", 22, "uni3")
    ));

    StudentService studentService = new StudentService(studentDb);


    //WHEN
    List<Student> studentsResult = studentService.findStudentsByUniverstiy("uni1");

    //THEN
    assertEquals(2, studentsResult.size());
    assertTrue(studentsResult.contains(new Student("12", "Frank", 12, "uni1")));
    assertTrue(studentsResult.contains(new Student("10", "Marwin", 32, "uni1")));

  }

}
