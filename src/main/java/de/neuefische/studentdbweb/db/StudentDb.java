package de.neuefische.studentdbweb.db;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDb {

  private final List<Student> students = new ArrayList<>(List.of(
      new Student("1", "Frank", 22, "uni1"),
      new Student("2", "Marwin", 25, "uni2"),
      new Student("3", "Caro", 18, "uni3"),
      new Student("4", "Franziska", 23, "uni1"),
      new Student("f5", "Sabine", 29, "uni2"),
      new Student("6", "Fabian", 30, "uni1")
  ));


  public List<Student> getStudents(){
    return students;
  }


  public void add(Student student) {
    this.students.add(student);
  }
}
