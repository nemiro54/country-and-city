package com.nemiro54.countryandcity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(

    @Schema(example = "nemiro54")
    String username,
    @Schema(example = "123")
    String password
) {

}
