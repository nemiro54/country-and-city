package com.nemiro54.countryandcity.util;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Validator {

  public static boolean isJwtTokenFormatValid(String token) {
    String jwtPattern = "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+$";
    return Pattern.matches(jwtPattern, token);
  }
}
