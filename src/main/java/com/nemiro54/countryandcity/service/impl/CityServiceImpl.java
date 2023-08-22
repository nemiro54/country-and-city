package com.nemiro54.countryandcity.service.impl;

import com.nemiro54.countryandcity.dto.request.UpdateCityRequestDto;
import com.nemiro54.countryandcity.dto.response.CityDto;
import com.nemiro54.countryandcity.exception.notfound.NotFoundException;
import com.nemiro54.countryandcity.mapper.CityMapper;
import com.nemiro54.countryandcity.model.City;
import com.nemiro54.countryandcity.repository.CityRepository;
import com.nemiro54.countryandcity.service.CityService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

  private final CityRepository cityRepository;
  private final CityMapper cityMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<CityDto> getCities(Pageable pageable) {
    return cityMapper.toDto(cityRepository.findAll(pageable));
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CityDto> getCitiesByCountryName(String countryName, Pageable pageable) {
    return cityMapper.toDto(cityRepository.findCitiesByCountryName(countryName, pageable));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CityDto> getCity(String name) {
    return cityMapper.toDto(cityRepository.findCityByNameIgnoreCase(name));
  }

  @Override
  @Transactional
  public CityDto updateCity(UUID id, UpdateCityRequestDto dto) {
    City city = cityRepository.findById(id).orElseThrow(() ->
        new NotFoundException(String.format("City not found, id='%s'", id))
    );
    City updatedCity = updateCity(city, dto);
    return cityMapper.toDto(updatedCity);
  }

  private City updateCity(City city, UpdateCityRequestDto dto) {
    if (dto.name() != null) {
      city.setName(dto.name());
    }
    if (dto.flagUrl() != null) {
      city.setFlagUrl(dto.flagUrl());
    }
    return cityRepository.save(city);
  }
}
