# Country and City App

The **Country and City App** project is a Java Spring Boot application that allows users to browse through a paginated list of cities and countries. It provides various features for managing city and country data.

## Features

- Browse through a paginated list of cities with country flags.
- Browse through a paginated list of countries.
- Get all cities by the name of the country.
- Search for cities by name.
- Edit city details, including the name and logo (requires "EDITOR" role).

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Apache Maven
- PostgreSQL database
- Spring Boot
- Spring Security

## Getting Started

### Installation and Usage

1. Clone the repository:

   ```sh
   git clone https://github.com/nemiro54/country-and-city.git
   ```
2. Move to the repository directory
   ```sh
   cd country-and-city
   ```

3. Build and run
   ```sh
   docker-compose up
   ```

The application will start, and you can access it via http://localhost:8080 in your web browser.

## Endpoints

The application provides the following endpoints:

* /auth/register: Sign up a new user (POST).
* /auth/login: Sign in by username and password (POST).
* /cities: Get all cities (paged) (GET).
* /cities/country/{countryName}: Get all cities by country name (paged) (GET).
* /cities/{name}: Get a city by name (GET).
* /cities/{id}: Update a city (PATCH) - Requires "EDITOR" role.
* /countries: Get all countries (paged) (GET).

## Swagger API

The Swagger API documentation for this project is available at http://localhost:8080/swagger-ui/index.html. You can use Swagger to explore and interact with the available endpoints.
