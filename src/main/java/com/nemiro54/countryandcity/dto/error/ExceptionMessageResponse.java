package com.nemiro54.countryandcity.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

public record ExceptionMessageResponse(
    @Schema(example = "2022-04-16T23:07:59.441143700Z")
    Instant time,
    @Schema(example = "Operation completed unsuccessfully")
    String message,
    @Schema(example = "Access Denied")
    String reason
) {

}
