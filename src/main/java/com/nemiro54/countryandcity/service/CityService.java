package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.request.UpdateCityRequestDto;
import com.nemiro54.countryandcity.dto.response.CityDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface CityService {

  Page<CityDto> getCities(Pageable pageable);

  Page<CityDto> getCitiesByCountryName(String countryName, Pageable pageable);

  List<CityDto> getCity(String name);

  CityDto updateCity(UUID id, UpdateCityRequestDto dto);
}
