package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.Role;
import ru.kashtanov.graduation_work.repositories.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Integer roleId) {
        return roleRepository.findRoleById(roleId).orElse(null);
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElse(null);
    }
}
