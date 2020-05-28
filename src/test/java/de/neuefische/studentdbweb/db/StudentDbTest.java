package de.neuefische.studentdbweb.db;

import de.neuefische.studentdbweb.model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
