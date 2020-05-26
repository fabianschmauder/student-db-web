package de.neuefische.studentdbweb.api;

import de.neuefische.studentdbweb.model.CovidApiCountryStats;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Covid19Api {


  public int numberOfActiveInfections(){
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.covid19api.com/total/country/germany?from=2020-05-24T00:00:00Z&to=2020-05-25T00:00:00Z";

    ResponseEntity<CovidApiCountryStats[]> responseEntity = restTemplate.getForEntity(url, CovidApiCountryStats[].class);

    CovidApiCountryStats[] covidData = responseEntity.getBody();


    if(covidData.length > 0){
      return covidData[0].getActive();
    }
    throw new IllegalStateException("No data form covid api");
  }

}
