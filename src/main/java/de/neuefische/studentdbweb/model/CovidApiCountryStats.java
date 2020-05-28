package de.neuefische.studentdbweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidApiCountryStats {

  @JsonProperty("Active")
  private int active;

  @JsonProperty("Date")
  private LocalDateTime date;

}
