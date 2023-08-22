package com.nemiro54.countryandcity.service;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthorizationResponse;

public interface AuthorizationService {

  /**
   * Register a new user.
   *
   * @param request The registration request containing user details.
   * @return A {@link AuthorizationResponse} containing the access JWT token.
   */
  AuthorizationResponse register(RegisterRequest request);

  /**
   * Log in a user.
   *
   * @param request The login request containing user credentials.
   * @return A {@link AuthorizationResponse} containing the access JWT token.
   */
  AuthorizationResponse login(LoginRequest request);
}
