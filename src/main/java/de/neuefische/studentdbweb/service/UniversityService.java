package de.neuefische.studentdbweb.service;

import de.neuefische.studentdbweb.api.Covid19Api;
import de.neuefische.studentdbweb.model.AlarmStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

  private final Covid19Api api;

  private final AlarmStatus alarmStatus = AlarmStatus.GREEN;

  @Autowired
  public UniversityService(Covid19Api api) {
    this.api = api;
  }

  public boolean isOpen() {
    return api.numberOfActiveInfections() <= getNumberOfAllowedInfections();
  }

  private int getNumberOfAllowedInfections() {
    switch (alarmStatus) {
      case RED:
        return 0;
      case ORANGE:
        return 1000;
      case GREEN:
        return 15000;
      default:
        return 0;
    }
  }

  public AlarmStatus getAlarmStatus() {
    return alarmStatus;
  }
}
