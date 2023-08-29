package com.nemiro54.countryandcity.controller.impl;

import static com.nemiro54.countryandcity.util.PredefinedEntity.LOGIN_REQUEST;
import static com.nemiro54.countryandcity.util.PredefinedEntity.REGISTER_REQUEST;
import static com.nemiro54.countryandcity.util.PredefinedEntity.ROLE_EDITOR;
import static com.nemiro54.countryandcity.util.PredefinedEntity.USER;
import static com.nemiro54.countryandcity.util.Validator.isJwtTokenFormatValid;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nemiro54.countryandcity.controller.AbstractWebMvcTest;
import com.nemiro54.countryandcity.dto.response.AuthorizationResponse;
import com.nemiro54.countryandcity.model.Role;
import com.nemiro54.countryandcity.repository.RoleRepository;
import com.nemiro54.countryandcity.repository.UserRepository;
import java.util.Set;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AuthorizationControllerTest extends AbstractWebMvcTest {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;

  @BeforeEach
  void setup() {
    Role role = roleRepository.save(ROLE_EDITOR);
    USER.setRoles(Set.of(role));
    userRepository.save(USER);
  }

  @Test
  @SneakyThrows
  void register() {
    // Setup
    String request = mapper.writeValueAsString(REGISTER_REQUEST);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            post("/auth/register")
                .contentType(APPLICATION_JSON)
                .content(request)
                .accept(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    AuthorizationResponse authorizationResponse = mapper.readValue(
        response.getContentAsString(), AuthorizationResponse.class
    );

    // Verify the result
    assertTrue(isJwtTokenFormatValid(authorizationResponse.token()));
  }

  @Test
  @SneakyThrows
  void login() {
    // Setup
    String request = mapper.writeValueAsString(LOGIN_REQUEST);

    // Run the test
    MockHttpServletResponse response = mockMvc.perform(
            post("/auth/login")
                .contentType(APPLICATION_JSON)
                .content(request)
                .accept(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

    AuthorizationResponse authorizationResponse = mapper.readValue(
        response.getContentAsString(), AuthorizationResponse.class
    );

    // Verify the result
    assertTrue(isJwtTokenFormatValid(authorizationResponse.token()));
  }
}