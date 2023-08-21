package com.nemiro54.countryandcity.service.impl;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthenticationResponse;
import com.nemiro54.countryandcity.exception.notfound.NotFoundException;
import com.nemiro54.countryandcity.model.Role;
import com.nemiro54.countryandcity.model.User;
import com.nemiro54.countryandcity.repository.UserRepository;
import com.nemiro54.countryandcity.security.JwtService;
import com.nemiro54.countryandcity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  @Transactional
  public AuthenticationResponse register(RegisterRequest request) {
    User user = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public AuthenticationResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()
        )
    );
    User user = userRepository.findUserByUsername(request.getUsername())
        .orElseThrow(() -> new NotFoundException(
            String.format("User not found, username: %s", request.getUsername()))
        );
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
