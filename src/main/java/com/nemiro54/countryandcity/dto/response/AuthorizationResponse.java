package com.nemiro54.countryandcity.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthorizationResponse(
    @Schema(example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZW1pcm81NCIsImlhdCI6MTY5MjcyMDMzNCwiZXhwIjoxNjkyNzIzOTM0fQ.VwclFVFDMjzjS5feU44DzMXZEkT3_HP3onHjCCSbJa4")
    String token
) {

}
