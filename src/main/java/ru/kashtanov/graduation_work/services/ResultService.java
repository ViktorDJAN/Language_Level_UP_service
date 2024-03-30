package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.kashtanov.graduation_work.models.Result;
import ru.kashtanov.graduation_work.repositories.ResultRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    private final StudentService studentService;
    private final ResultRepository resultRepository;

    public ResultService(StudentService studentService,
                         ResultRepository resultRepository) {
        this.studentService = studentService;
        this.resultRepository = resultRepository;
    }

    public Result getLastStudentResultById(Integer studentId) {
        List<Result> resultList = resultRepository.findLastStudentResultsById(studentId);
        Result result = resultList.get(resultList.size() - 1);

        return result;


    }

    public void addResultToDataBase(Result result) {
        resultRepository.save(result);
    }
}
