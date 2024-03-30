package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.Result;


import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {


    @Query("SELECT r from Result r where r.student.id=?1")
    List<Result> findLastStudentResultsById(Integer studentId);


}
