package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

  private final List<Student> students = new ArrayList<>(List.of(
      new Student("1","Frank",22),
      new Student("2","Marwin", 25),
      new Student("3","Caro", 18),
      new Student("4","Franziska",23),
      new Student("f5","Sabine", 29),
      new Student("6","Fabian",30)
  ));

  @GetMapping
  public List<Student> getStudents(@RequestParam(name="q", required = false) String query,@RequestParam(required = false) int minage) {
    if(query == null){
      return students;
    }
    ArrayList<Student> matchingStudents = new ArrayList<>();

    for (Student student : students) {
      if(studentMatchQuery(student,query,minage)){
        matchingStudents.add(student);
      }
    }

    return matchingStudents;
  }

  private boolean studentMatchQuery(Student student, String query, int minage){
    if(student.getAge() < minage){
      return  false;
    }

    if(student.getName().toLowerCase().startsWith(query.toLowerCase())){
      return true;
    }

    if(student.getId().toLowerCase().startsWith(query.toLowerCase())){
      return true;
    }
    return false;
  }


  @PutMapping
  public Student addStudent(@RequestBody Student student) {
    if (student.getId() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id not set");
    }
    students.add(student);
    return student;
  }

  @GetMapping("{id}")
  public Student getStudentById(@PathVariable String id) {
    for (Student student : students) {
      if (student.getId().equals(id)) {
        return student;
      }
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
  }

  @DeleteMapping("{id}")
  public Student deleteStudent(@PathVariable String id){
    Student studentToDelete = getStudentById(id);
    students.remove(studentToDelete);
    return studentToDelete;
  }
}
