package com.nemiro54.countryandcity.repository;

import com.nemiro54.countryandcity.model.Country;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

  boolean existsByName(String country);

  Optional<Country> findByAlpha3Code(String iso3);
}
