package com.nemiro54.countryandcity.controller.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.nemiro54.countryandcity.controller.AbstractWebMvcTest;
import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.model.Country;
import com.nemiro54.countryandcity.repository.CountryRepository;
import com.nemiro54.countryandcity.util.PredefinedEntity;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;

class CountryControllerTest extends AbstractWebMvcTest {

  private static Country country;

  @Autowired
  private CountryRepository countryRepository;

  @BeforeAll
  static void setup() {
    country = PredefinedEntity.COUNTRY;
  }

  @Test
  @WithMockUser
  @SneakyThrows
  void getCountries() {
    // Setup
    country = countryRepository.save(country);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            get("/countries")
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    JsonNode rootNode = mapper.readTree(response.getContentAsString());
    JsonNode contentNode = rootNode.get("content");

    List<CountryDto> countryDtos = mapper.convertValue(contentNode, new TypeReference<>() {
        }
    );

    // Verify the results
    for (CountryDto countryDto : countryDtos) {
      assertTrue(countryRepository.existsById(countryDto.id()));
    }

    // Delete test data
    countryRepository.deleteAll();
  }
}