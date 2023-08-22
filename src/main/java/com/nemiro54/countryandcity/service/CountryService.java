package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

  Page<CountryDto> getCountries(Pageable pageable);
}
