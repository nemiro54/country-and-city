package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthorizationResponse;

public interface AuthenticationService {

  AuthorizationResponse register(RegisterRequest request);

  AuthorizationResponse login(LoginRequest request);
}
