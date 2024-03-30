package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.Tutor;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    @Query("SELECT t FROM Tutor t WHERE t.username = ?1")
    Optional<Tutor> findTutorByUserName(String tutorUsername);

    @Query("SELECT t FROM Tutor t WHERE t.firstName = ?1")
    Optional<Tutor> findTutorByFirstName(String tutorFirstName);




}
