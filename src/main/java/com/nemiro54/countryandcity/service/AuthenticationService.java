package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthenticationResponse;

public interface AuthenticationService {

  AuthenticationResponse register(RegisterRequest request);

  AuthenticationResponse login(LoginRequest request);
}
