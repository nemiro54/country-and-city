package com.nemiro54.countryandcity.service.impl;

import static com.nemiro54.countryandcity.util.PredefinedEntity.COUNTRY;
import static com.nemiro54.countryandcity.util.PredefinedEntity.COUNTRY_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.mapper.CountryMapper;
import com.nemiro54.countryandcity.model.Country;
import com.nemiro54.countryandcity.repository.CountryRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

  @Mock
  private CountryRepository countryRepository;
  @Mock
  private CountryMapper countryMapper;
  @InjectMocks
  private CountryServiceImpl countryService;

  @Test
  void getCountries() {
    // Setup
    Pageable pageable = Pageable.ofSize(10).withPage(0);
    Page<Country> countryPage = new PageImpl<>(List.of(COUNTRY), pageable, 1);
    Page<CountryDto> countryDtoPage = new PageImpl<>(List.of(COUNTRY_DTO), pageable, 1);

    when(countryRepository.findAll(any(Pageable.class))).thenReturn(countryPage);
    when(countryMapper.toDto(any(Page.class))).thenReturn(countryDtoPage);

    // Run the test
    Page<CountryDto> actualPage = countryService.getCountries(pageable);

    // Verify the test
    assertEquals(countryDtoPage, actualPage);
  }
}