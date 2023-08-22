package com.nemiro54.countryandcity.service.impl;

import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.mapper.CountryMapper;
import com.nemiro54.countryandcity.repository.CountryRepository;
import com.nemiro54.countryandcity.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
  private final CountryMapper countryMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<CountryDto> getCountries(Pageable pageable) {
    return countryMapper.toDto(countryRepository.findAll(pageable));
  }
}
