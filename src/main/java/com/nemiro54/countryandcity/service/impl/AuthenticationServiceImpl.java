package com.nemiro54.countryandcity.service.impl;

import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.response.AuthenticationResponse;
import com.nemiro54.countryandcity.exception.notfound.NotFoundException;
import com.nemiro54.countryandcity.model.User;
import com.nemiro54.countryandcity.repository.UserRepository;
import com.nemiro54.countryandcity.security.JwtService;
import com.nemiro54.countryandcity.service.AuthenticationService;
import java.util.HashSet;
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
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  @Transactional
  public AuthenticationResponse register(RegisterRequest request) {
    User user = User.builder()
        .username(request.username())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .roles(new HashSet<>())
        .build();
    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user.getUsername());
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @Override
  @Transactional
  public AuthenticationResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()
        )
    );
    User user = userRepository.findUserByUsername(request.username())
        .orElseThrow(() -> new NotFoundException(
            String.format("User not found, username: %s", request.username()))
        );
    String jwtToken = jwtService.generateToken(user.getUsername());
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
