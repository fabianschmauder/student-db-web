package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

  private final List<Student> students = new ArrayList<>(List.of(
//      new Student("1", "Frank", 22, "uni1"),
//      new Student("2", "Marwin", 25, "uni2"),
//      new Student("3", "Caro", 18, "uni3"),
//      new Student("4", "Franziska", 23, "uni1"),
//      new Student("f5", "Sabine", 29, "uni2"),
//      new Student("6", "Fabian", 30, "uni1")
  ));

  public StudentService() {
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public List<Student> getStudents() {
    return students;
  }

  public List<Student> findStudentsByUniverstiy(String universityId) {

    List<Student> result = new ArrayList<>();

    for (Student student : students) {
      if (student.getUniversityId().equals(universityId)) {
        result.add(student);
      }
    }

    return result;
  }
}
