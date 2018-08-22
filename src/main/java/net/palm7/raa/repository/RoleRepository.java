package net.palm7.raa.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.palm7.raa.model.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByRole(Role.Roles role);

}
