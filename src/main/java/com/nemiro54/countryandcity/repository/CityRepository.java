package com.nemiro54.countryandcity.repository;

import com.nemiro54.countryandcity.model.City;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, UUID> {

  List<City> findByCountryName(String countryName);

  List<City> findCitiesByNameContainingIgnoreCase(String cityName);
}
