package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.request.UpdateCityRequestDto;
import com.nemiro54.countryandcity.dto.CityDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface CityService {

  /**
   * Retrieve a paginated list of cities.
   *
   * @param pageable The pagination information.
   * @return A {@link Page} of {@link CityDto} objects representing cities.
   */
  Page<CityDto> getCities(Pageable pageable);

  /**
   * Retrieve a paginated list of cities by the name of the country they belong to.
   *
   * @param countryName The name of the country.
   * @param pageable    The pagination information.
   * @return A {@link Page} of {@link CityDto} objects representing cities in the specified country.
   */
  Page<CityDto> getCitiesByCountryName(String countryName, Pageable pageable);

  /**
   * Retrieve a list of cities by name.
   *
   * @param name The name of the city to search for.
   * @return A {@link List} of {@link CityDto} objects matching the city name.
   */
  List<CityDto> getCity(String name);

  /**
   * Update the details of a city.
   *
   * @param id  The unique identifier of the city to update.
   * @param dto The {@link UpdateCityRequestDto} containing the updated city details.
   * @return A {@link CityDto} representing the updated city.
   */
  CityDto updateCity(UUID id, UpdateCityRequestDto dto);
}
