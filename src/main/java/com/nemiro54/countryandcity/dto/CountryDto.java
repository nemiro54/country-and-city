package com.nemiro54.countryandcity.dto;

import java.util.UUID;

public record CountryDto(
    UUID id,
    String name,
    String flagUrl
) {

}
