package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.tutor.username = ?1")
    List<Student> findAllStudentsByUserName(String tutorName);

    @Query("UPDATE  Student SET tutor.id=?1 where firstName=?2")
    Optional<Student> updateTutorUserId(Integer tutorId, String firstName);

    @Query("SELECT s FROM Student s WHERE s.username = ?1")
    Optional<Student> findStudentByUserName(String studentUsername);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1")
    Optional<Student> findStudentByUserFirstName(String studentFirstName);

    @Query("SELECT s FROM Student s WHERE s.tutor.id = null")
    List<Student> findAllStudentsWithoutTutor();

    @Query("SELECT s FROM Student s WHERE s.tutor.id = null and s.firstName=?1")
    Optional<Student> findCertainStudentWithoutTutorByName(String studentFirstName);
}
