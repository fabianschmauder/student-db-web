package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.api.Covid19Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

  private final Covid19Api api;

  @Autowired
  public UniversityService(Covid19Api api) {
    this.api = api;
  }

  public boolean isOpen() {
    return api.numberOfActiveInfections() <= 1000;
  }
}
