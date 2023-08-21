package com.nemiro54.countryandcity.service.impl;

import com.nemiro54.countryandcity.repository.CountryRepository;
import com.nemiro54.countryandcity.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
}
