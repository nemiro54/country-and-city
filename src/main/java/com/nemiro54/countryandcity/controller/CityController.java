package com.nemiro54.countryandcity.controller;

import com.nemiro54.countryandcity.dto.request.UpdateCityRequestDto;
import com.nemiro54.countryandcity.dto.CityDto;
import com.nemiro54.countryandcity.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "City Controller")
public class CityController {

  private final CityService cityService;

  @Operation(summary = "Get all cities (paged)", description = "Returns a page of cities")
  @GetMapping
  public ResponseEntity<Page<CityDto>> getCities(
      @ParameterObject @PageableDefault(size = 20) Pageable pageable
  ) {
    return ResponseEntity.ok(cityService.getCities(pageable));
  }

  @Operation(summary = "Get all cities by country name (paged)", description = "Returns a page of cities by country name")
  @GetMapping("/country/{countryName}")
  public ResponseEntity<Page<CityDto>> getCitiesByCountryName(
      @PathVariable String countryName,
      @ParameterObject @PageableDefault(size = 20) Pageable pageable
  ) {
    return ResponseEntity.ok(cityService.getCitiesByCountryName(countryName, pageable));
  }

  @Operation(summary = "Get a city by name", description = "Returns a city by name")
  @GetMapping("/{name}")
  public ResponseEntity<List<CityDto>> getCity(@PathVariable String name) {
    return ResponseEntity.ok(cityService.getCity(name));
  }

  @Operation(summary = "Update a city", description = "Updates a city")
  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority(T(com.nemiro54.countryandcity.security.AuthorityType).EDITOR)")
  public ResponseEntity<CityDto> updateCity(
      @PathVariable UUID id,
      @RequestBody UpdateCityRequestDto dto
  ) {
    return ResponseEntity.ok(cityService.updateCity(id, dto));
  }
}
