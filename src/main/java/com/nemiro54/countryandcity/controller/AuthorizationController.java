package com.nemiro54.countryandcity.controller;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthorizationResponse;
import com.nemiro54.countryandcity.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization Controller")
public class AuthorizationController {

  private final AuthorizationService service;

  @Operation(summary = "Sign Up a new user", description = "Registers a new user and returns a token")
  @PostMapping("/register")
  public ResponseEntity<AuthorizationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @Operation(summary = "Sign In by username and password", description = "Signs In and returns a token")
  @PostMapping("/login")
  public ResponseEntity<AuthorizationResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(service.login(request));
  }
}
