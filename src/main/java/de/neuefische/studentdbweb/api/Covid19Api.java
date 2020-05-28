package de.neuefische.studentdbweb.api;

import de.neuefische.studentdbweb.model.CovidApiCountryStats;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class Covid19Api {


  public int numberOfActiveInfections() {
    LocalDate currentDate = LocalDate.now();

    RestTemplate restTemplate = new RestTemplate();
    String currentDayFormatted = currentDate.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    String dayBeforeFormatted = currentDate.minusDays(1).atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);


    String url = "https://api.covid19api.com/total/country/germany?from=" + dayBeforeFormatted + "&to=" + currentDayFormatted;

    ResponseEntity<CovidApiCountryStats[]> responseEntity = restTemplate.getForEntity(url, CovidApiCountryStats[].class);

    CovidApiCountryStats[] covidData = responseEntity.getBody();


    if (covidData.length > 0) {
      return covidData[covidData.length - 1].getActive();
    }
    throw new IllegalStateException("No data form covid api");
  }

}
