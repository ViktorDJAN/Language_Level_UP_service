package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.Answer;
import ru.kashtanov.graduation_work.repositories.AnswerRepository;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }
}
