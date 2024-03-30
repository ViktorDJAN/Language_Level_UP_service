package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.QuestionAnswer;

import java.util.Optional;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    @Query("SELECT qa FROM QuestionAnswer qa WHERE qa.id =?1")
    Optional<QuestionAnswer> findQuestionAnswerBy(Long id);
}
