package com.nemiro54.countryandcity.model;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponse {

  private boolean error;
  private String msg;
  private List<CountryData> data;
}

