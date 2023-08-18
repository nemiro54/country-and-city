package com.nemiro54.countryandcity.repository;

import com.nemiro54.countryandcity.model.Country;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, UUID> {

}
