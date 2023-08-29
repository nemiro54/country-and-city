package com.nemiro54.countryandcity.controller.impl;

import static com.nemiro54.countryandcity.util.PredefinedEntity.UPDATE_CITY_REQUEST_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.nemiro54.countryandcity.controller.AbstractWebMvcTest;
import com.nemiro54.countryandcity.dto.CityDto;
import com.nemiro54.countryandcity.model.City;
import com.nemiro54.countryandcity.model.Country;
import com.nemiro54.countryandcity.repository.CityRepository;
import com.nemiro54.countryandcity.repository.CountryRepository;
import com.nemiro54.countryandcity.util.PredefinedEntity;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CityControllerTest extends AbstractWebMvcTest {

  private static Country country;
  private static City city;

  @Autowired
  private CityRepository cityRepository;
  @Autowired
  private CountryRepository countryRepository;

  @BeforeAll
  static void setup() {
    country = PredefinedEntity.COUNTRY;
    city = PredefinedEntity.CITY;
  }

  @Test
  @WithMockUser
  @SneakyThrows
  void getCities() {
    // Setup
    country = countryRepository.save(country);
    city.setCountry(country);
    city = cityRepository.save(city);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            get("/cities")
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    JsonNode rootNode = mapper.readTree(response.getContentAsString());
    JsonNode contentNode = rootNode.get("content");
    List<CityDto> cityDtos = mapper.convertValue(contentNode, new TypeReference<>() {
    });

    // Verify the result
    for (CityDto cityDto : cityDtos) {
      assertTrue(cityRepository.existsById(cityDto.id()));
    }

    // Delete test data
    cityRepository.deleteAll();
    countryRepository.deleteAll();
  }

  @Test
  @WithMockUser
  @SneakyThrows
  void getCitiesByCountryName() {
    // Setup
    country = countryRepository.save(country);
    city.setCountry(country);
    city = cityRepository.save(city);
    String countryName = country.getName();

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            get("/cities/country/{countryName}", countryName)
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    JsonNode rootNode = mapper.readTree(response.getContentAsString());
    JsonNode contentNode = rootNode.get("content");
    List<CityDto> cityDtos = mapper.convertValue(contentNode, new TypeReference<>() {
    });

    // Verify the result
    for (CityDto cityDto : cityDtos) {
      assertTrue(cityRepository.existsById(cityDto.id()));
      assertEquals(countryName, cityDto.countryName());
    }

    // Delete test data
    cityRepository.deleteAll();
    countryRepository.deleteAll();
  }

  @Test
  @WithMockUser
  @SneakyThrows
  void getCity() {
    // Setup
    country = countryRepository.save(country);
    city.setCountry(country);
    city = cityRepository.save(city);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            get("/cities/{name}", city.getName())
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    List<CityDto> actualCityDtos = mapper.readValue(
        response.getContentAsString(), new TypeReference<>() {
        }
    );

    // Verify the result
    for (CityDto cityDto : actualCityDtos) {
      assertTrue(cityRepository.existsById(cityDto.id()));
    }

    // Delete test data
    cityRepository.deleteAll();
    countryRepository.deleteAll();
  }

  @Test
  @WithMockUser(authorities = "EDITOR")
  @SneakyThrows
  void updateCity() {
    // Setup
    country = countryRepository.save(country);
    city.setCountry(country);
    city = cityRepository.save(city);
    String request = mapper.writeValueAsString(UPDATE_CITY_REQUEST_DTO);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            patch("/cities/{id}", city.getId())
                .contentType(APPLICATION_JSON)
                .content(request)
                .accept(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    CityDto updatedCityDto = mapper.readValue(response.getContentAsString(), CityDto.class);
    City updatedCity = cityRepository.findById(city.getId())
        .orElseThrow();

    // Verify the result
    assertEquals(UPDATE_CITY_REQUEST_DTO.name(), updatedCityDto.name());
    assertEquals(UPDATE_CITY_REQUEST_DTO.flagUrl(), updatedCityDto.flagUrl());
    assertEquals(UPDATE_CITY_REQUEST_DTO.name(), updatedCity.getName());
    assertEquals(UPDATE_CITY_REQUEST_DTO.flagUrl(), updatedCity.getFlagUrl());

    // Delete test data
    cityRepository.deleteAll();
    countryRepository.deleteAll();
  }

  @Test
  @WithMockUser
  @SneakyThrows
  void updateCityWithNotEnoughRights() {
    // Setup
    country = countryRepository.save(country);
    city.setCountry(country);
    city = cityRepository.save(city);
    String request = mapper.writeValueAsString(UPDATE_CITY_REQUEST_DTO);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            patch("/cities/{id}", city.getId())
                .contentType(APPLICATION_JSON)
                .content(request)
                .accept(APPLICATION_JSON)
        )
        .andExpect(status().isForbidden())
        .andReturn()
        .getResponse();

    // Delete test data
    cityRepository.deleteAll();
    countryRepository.deleteAll();
  }
}