package com.nemiro54.countryandcity.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateCityRequestDto(
    @NotNull
    String name,
    @NotNull
    String flagUrl
) {

}
