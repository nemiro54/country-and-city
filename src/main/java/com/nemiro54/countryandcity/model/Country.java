package com.nemiro54.countryandcity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "countries")
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;

  @Column(unique = true)
  String name;

  @Column(name = "alpha3_code", unique = true)
  String alpha3Code;

  String flagUrl;
}
