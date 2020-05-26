package de.neuefische.studentdbweb.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  private String id;
  private String name;
  private int age;
  private String universityId;
}
