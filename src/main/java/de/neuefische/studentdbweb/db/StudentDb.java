package de.neuefische.studentdbweb.db;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDb {

  private final List<Student> students = new ArrayList<>();


  public List<Student> getStudents(){
    return students;
  }


  public void add(Student student) {
    this.students.add(student);
  }

  public void clearDb(){
    students.clear();
  }
}
