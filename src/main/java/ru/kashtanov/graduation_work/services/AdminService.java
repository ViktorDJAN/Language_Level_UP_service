package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.repositories.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;


    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
