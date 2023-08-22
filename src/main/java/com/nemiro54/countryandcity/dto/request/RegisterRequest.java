package com.nemiro54.countryandcity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequest (
    @Schema(example = "nemiro54")
    String username,
    @Schema(example = "nemiro54@gmail.com")
    String email,
    @Schema(example = "123")
    String password
) {

}
