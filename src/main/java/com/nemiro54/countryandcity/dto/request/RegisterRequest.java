package com.nemiro54.countryandcity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
    @NotNull
    @Schema(example = "test")
    String username,
    @NotNull
    @Schema(example = "test@gmail.com")
    String email,
    @NotNull
    @Schema(example = "123")
    String password
) {

}
