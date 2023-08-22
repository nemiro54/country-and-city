package com.nemiro54.countryandcity.mapper;

import com.nemiro54.countryandcity.dto.CityDto;
import com.nemiro54.countryandcity.model.City;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

  @Mapping(target = "countryName", source = "country.name")
  CityDto toDto(City city);

  default Page<CityDto> toDto(Page<City> cities) {
    return cities.map(this::toDto);
  }

  default List<CityDto> toDto(List<City> cities) {
    return cities.stream()
        .map(this::toDto)
        .toList();
  }
}
