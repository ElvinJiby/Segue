package org.elvinjiby.marketplace.repository;

import org.elvinjiby.marketplace.model.Role;
import org.elvinjiby.marketplace.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(UserRole role);
}
