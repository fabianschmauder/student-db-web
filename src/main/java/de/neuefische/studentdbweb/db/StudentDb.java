package de.neuefische.studentdbweb.db;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDb {

  private final List<Student> students = new ArrayList<>();


  public List<Student> getStudents(String query){
    if(query == null || query.isBlank()){
      return students;
    }

    List<Student> filteredStudents = new ArrayList<>();

    for (Student student : students) {
      if(student.getName() != null && student.getName().toLowerCase().contains(query.toLowerCase())){
        filteredStudents.add(student);
      }
    }
    return filteredStudents;
  }

 public List<Student> getStudents(){
    return getStudents(null);
  }


  public void add(Student student) {
    this.students.add(student);
  }

  public void clearDb(){
    students.clear();
  }
}
