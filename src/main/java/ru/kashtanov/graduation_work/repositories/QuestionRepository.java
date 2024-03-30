package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kashtanov.graduation_work.models.Answer;
import ru.kashtanov.graduation_work.models.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.qId =?1")
    Optional<Answer> findQuestionById(Long id);
}
