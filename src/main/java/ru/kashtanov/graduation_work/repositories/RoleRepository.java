package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String username);

    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Optional<Role> findRoleById(Integer roleId);
}
