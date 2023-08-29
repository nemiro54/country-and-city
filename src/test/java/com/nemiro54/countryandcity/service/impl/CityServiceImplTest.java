package com.nemiro54.countryandcity.service.impl;

import static com.nemiro54.countryandcity.util.PredefinedEntity.CITY;
import static com.nemiro54.countryandcity.util.PredefinedEntity.CITY_DTO;
import static com.nemiro54.countryandcity.util.PredefinedEntity.UPDATE_CITY_REQUEST_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nemiro54.countryandcity.dto.CityDto;
import com.nemiro54.countryandcity.mapper.CityMapper;
import com.nemiro54.countryandcity.model.City;
import com.nemiro54.countryandcity.repository.CityRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

  @Mock
  private CityRepository cityRepository;
  @Mock
  private CityMapper cityMapper;
  @InjectMocks
  private CityServiceImpl cityService;

  @Test
  void getCities() {
    // Setup
    Pageable pageable = Pageable.ofSize(10).withPage(0);
    Page<City> cityPage = new PageImpl<>(List.of(CITY), pageable, 1);
    Page<CityDto> cityDtoPage = new PageImpl<>(List.of(CITY_DTO), pageable, 1);

    when(cityRepository.findAll(any(Pageable.class))).thenReturn(cityPage);
    when(cityMapper.toDto(any(Page.class))).thenReturn(cityDtoPage);

    // Run the test
    Page<CityDto> actualPage = cityService.getCities(pageable);

    // Verify the results
    assertEquals(cityDtoPage, actualPage);
  }

  @Test
  void getCitiesByCountryName() {
    // Setup
    Pageable pageable = Pageable.ofSize(10).withPage(0);
    Page<City> cityPage = new PageImpl<>(List.of(CITY), pageable, 1);
    Page<CityDto> cityDtoPage = new PageImpl<>(List.of(CITY_DTO), pageable, 1);

    when(cityRepository.findCitiesByCountryName(anyString(), any(Pageable.class))).thenReturn(
        cityPage);
    when(cityMapper.toDto(cityPage)).thenReturn(cityDtoPage);

    // Runt the test
    Page<CityDto> actualDto = cityService.getCitiesByCountryName("Belarus", pageable);

    // Verify the result
    assertEquals(cityDtoPage, actualDto);
  }

  @Test
  void getCity() {
    // Setup
    when(cityRepository.findCityByNameIgnoreCase(anyString())).thenReturn(List.of(CITY));
    when(cityMapper.toDto(any(List.class))).thenReturn(List.of(CITY_DTO));

    // Run the test
    List<CityDto> actualDto = cityService.getCity("Minsk");

    // Verify the results
    assertEquals(List.of(CITY_DTO), actualDto);
  }

  @Test
  void updateCity() {
    // Setup
    CityDto expectedCityDto = new CityDto(
        CITY_DTO.id(), UPDATE_CITY_REQUEST_DTO.name(), CITY_DTO.countryName(), UPDATE_CITY_REQUEST_DTO.flagUrl()
    );
    when(cityRepository.findById(any(UUID.class))).thenReturn(Optional.of(CITY));
    when(cityRepository.save(any(City.class))).thenReturn(CITY);
    when(cityMapper.toDto(any(City.class))).thenReturn(expectedCityDto);

    // Run the test
    CityDto actualCityDto = cityService.updateCity(UUID.randomUUID(), UPDATE_CITY_REQUEST_DTO);

    // Verify the results
    assertEquals(expectedCityDto, actualCityDto);
  }
}