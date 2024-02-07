package com.george.server.calendar.repository;

import com.george.server.calendar.model.Role;
import com.george.server.calendar.util.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}