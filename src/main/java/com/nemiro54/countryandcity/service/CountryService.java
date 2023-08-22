package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

  /**
   * Retrieve a paginated list of countries.
   *
   * @param pageable The pagination information.
   * @return A {@link Page} of {@link CountryDto} objects representing countries.
   */
  Page<CountryDto> getCountries(Pageable pageable);
}
