package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
