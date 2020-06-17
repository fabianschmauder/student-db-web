package de.neuefische.studentdbweb.db;

import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class StudentDbTest {

  @Test
  public void addStudentShouldAddAStudentToTheDb() {
    //GIVEN
    StudentDb db = new StudentDb();
    Student studentToAdd = new Student("12", "Frank", 12, "uni1");

    //WHEN
    db.add(studentToAdd);

    //THEN
    List<Student> students = db.getStudents();
    Student studentToCheck = new Student("12", "Frank", 12, "uni1");
    assertTrue(students.contains(studentToCheck));
  }

  @Test
  public void getStudentsWithQueryShouldFilterStudents() {
    //GIVEN
    StudentDb db = new StudentDb();
    db.add(new Student("12", "Frank", 12, "uni1"));
    db.add(new Student("12", "Jochen", 13, "uni2"));
    db.add(new Student("33", "Franzi", 22, "uni3"));
    //WHEN

    List<Student> studentsResult = db.getStudents("Fr");

    //THEN
    assertThat(studentsResult, containsInAnyOrder(
        new Student("12", "Frank", 12, "uni1"),
        new Student("33", "Franzi", 22, "uni3")));
  }

}
