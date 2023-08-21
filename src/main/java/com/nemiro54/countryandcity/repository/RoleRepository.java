package com.nemiro54.countryandcity.repository;

import com.nemiro54.countryandcity.model.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByName(String name);
}
