package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.db.StudentDb;
import de.neuefische.studentdbweb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
  private final StudentDb studentDb;

  @Autowired
  public StudentService(StudentDb studentDb) {
    this.studentDb = studentDb;
  }

  public void addStudent(Student student) {

    studentDb.add(student);
  }

  public List<Student> getStudents(String query) {
    return studentDb.getStudents(query);
  }

  public List<Student> findStudentsByUniverstiy(String universityId) {
    List<Student> result = new ArrayList<>();
    List<Student> students = studentDb.getStudents();
    for (Student student : students) {
      if (student.getUniversityId().equals(universityId)) {
        result.add(student);
      }
    }

    return result;
  }
}
