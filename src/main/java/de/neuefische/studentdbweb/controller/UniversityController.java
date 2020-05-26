package de.neuefische.studentdbweb.controller;

import de.neuefische.studentdbweb.model.Student;
import de.neuefische.studentdbweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("university")
public class UniversityController {

  private final StudentService service;

  @Autowired
  public UniversityController(StudentService service) {
    this.service = service;
  }

  @GetMapping("{universityId}")
  public List<Student> getStudents(@PathVariable String universityId){
    return service.findStudentsByUniverstiy(universityId);
  }

}
