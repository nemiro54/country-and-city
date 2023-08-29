package com.nemiro54.countryandcity.dto;

import java.util.UUID;

public record CityDto(
    UUID id,
    String name,
    String countryName,
    String flagUrl
) {

}
