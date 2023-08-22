package com.nemiro54.countryandcity.mapper;

import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {

  CountryDto toDto(Country country);

  default Page<CountryDto> toDto(Page<Country> countries) {
    return countries.map(this::toDto);
  }
}
