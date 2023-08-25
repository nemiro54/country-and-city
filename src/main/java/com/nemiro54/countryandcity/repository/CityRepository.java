package com.nemiro54.countryandcity.repository;

import com.nemiro54.countryandcity.model.City;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

  @EntityGraph(attributePaths = "country")
  Page<City> findCitiesByCountryName(String countryName, Pageable pageable);

  @EntityGraph(attributePaths = "country")
  List<City> findCityByNameIgnoreCase(String name);

  @Override
  @EntityGraph(attributePaths = "country")
  Page<City> findAll(Pageable pageable);
}
