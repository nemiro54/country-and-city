package com.nemiro54.countryandcity.service.impl;

import static com.nemiro54.countryandcity.util.PredefinedEntity.JWT_TOKEN;
import static com.nemiro54.countryandcity.util.PredefinedEntity.LOGIN_REQUEST;
import static com.nemiro54.countryandcity.util.PredefinedEntity.REGISTER_REQUEST;
import static com.nemiro54.countryandcity.util.PredefinedEntity.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nemiro54.countryandcity.dto.response.AuthorizationResponse;
import com.nemiro54.countryandcity.model.User;
import com.nemiro54.countryandcity.repository.UserRepository;
import com.nemiro54.countryandcity.security.JwtService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceImplTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private JwtService jwtService;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private AuthenticationManager authManager;
  @InjectMocks
  private AuthorizationServiceImpl authService;

  @Test
  void register() {
    // Setup
    when(userRepository.save(any(User.class))).thenReturn(USER);
    when(jwtService.generateToken(anyString())).thenReturn(JWT_TOKEN);

    // Run the test
    AuthorizationResponse response = authService.register(REGISTER_REQUEST);

    // Verify the result
    assertEquals(JWT_TOKEN, response.token());
  }

  @Test
  void login() {
    // Setup
    when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(USER));
    when(jwtService.generateToken(anyString())).thenReturn(JWT_TOKEN);

    // Run the test
    AuthorizationResponse response = authService.login(LOGIN_REQUEST);

    // Verify the result
    assertEquals(JWT_TOKEN, response.token());
  }
}