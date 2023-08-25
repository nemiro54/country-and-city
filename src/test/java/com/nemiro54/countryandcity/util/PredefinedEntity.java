package com.nemiro54.countryandcity.util;

import com.nemiro54.countryandcity.dto.CityDto;
import com.nemiro54.countryandcity.dto.CountryDto;
import com.nemiro54.countryandcity.dto.request.LoginRequest;
import com.nemiro54.countryandcity.dto.request.RegisterRequest;
import com.nemiro54.countryandcity.dto.request.UpdateCityRequestDto;
import com.nemiro54.countryandcity.model.City;
import com.nemiro54.countryandcity.model.Country;
import com.nemiro54.countryandcity.model.Role;
import com.nemiro54.countryandcity.model.User;
import java.util.Set;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PredefinedEntity {

  public static final UUID DEFAULT_UUID = UUID.randomUUID();
  public static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZW1pcm81NCIsImlhdCI6MTY5MjcyMDMzNCwiZXhwIjoxNjkyNzIzOTM0fQ.VwclFVFDMjzjS5feU44DzMXZEkT3_HP3onHjCCSbJa4";


  public static final RegisterRequest REGISTER_REQUEST = new RegisterRequest(
      "username", "test@gmail.com", "123"
  );

  public static final LoginRequest LOGIN_REQUEST = new LoginRequest(
      "username", "123"
  );

  public static final Role ROLE_EDITOR = Role.builder()
      .id(DEFAULT_UUID)
      .name("EDITOR")
      .build();

  public static final User USER = User.builder()
      .id(DEFAULT_UUID)
      .username("username")
      .email("test@gmail.com")
      .password("$2a$10$Rbvf.SFAxfQSoGSqk/7QIesJECqDzRVUx2Kr2IHIzbHnr5HtFYsFe") // password: "123"
      .roles(Set.of(ROLE_EDITOR))
      .build();

  public static final Country COUNTRY = Country.builder()
      .id(DEFAULT_UUID)
      .name("Belarus")
      .alpha3Code("BLR")
      .flagUrl("/url")
      .build();

  public static final CountryDto COUNTRY_DTO = new CountryDto(
      "Belarus", "/url"
  );

  public static final City CITY = City.builder()
      .id(DEFAULT_UUID)
      .name("Minsk")
      .country(COUNTRY)
      .flagUrl("/url")
      .build();

  public static final CityDto CITY_DTO = new CityDto(
      "Minsk", "Belarus", "/url"
  );

  public static final UpdateCityRequestDto UPDATE_CITY_REQUEST_DTO = new UpdateCityRequestDto(
      "updatedName", "/updatedUrl"
  );
}
