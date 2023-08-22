package com.nemiro54.countryandcity.controller;

import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

  private final CountryService countryService;

  @Operation(summary = "Get all countries (paged)", description = "Returns a page of countries")
  @GetMapping
  public Page<CountryDto> getCountries(@PageableDefault(size = 20)Pageable pageable) {
    return countryService.getCountries(pageable);
  }
}
