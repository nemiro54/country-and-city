package com.nemiro54.countryandcity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequest(
    @Schema(example = "test")
    String username,
    @Schema(example = "test@gmail.com")
    String email,
    @Schema(example = "123")
    String password
) {

}
