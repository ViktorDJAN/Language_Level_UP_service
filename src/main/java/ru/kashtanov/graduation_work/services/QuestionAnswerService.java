package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.*;
import ru.kashtanov.graduation_work.dto.QuestionAnswerDto;
import ru.kashtanov.graduation_work.repositories.QuestionAnswerRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * QuestionAnswerService provides main features of the test page forming
 */
@Service
public class QuestionAnswerService {
    private final StudentService studentService;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ResultService resultService;

    public QuestionAnswerService(StudentService studentService,
                                 QuestionAnswerRepository questionAnswerRepository,
                                 QuestionService questionService,
                                 AnswerService answerService,
                                 ResultService resultService) {
        this.studentService = studentService;
        this.questionAnswerRepository = questionAnswerRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.resultService = resultService;
    }

    public List<QuestionAnswer> findAllAnswersQuestion() {
        return questionAnswerRepository.findAll();
    }

    public QuestionAnswer getCertainQuestionAnswerById(Long studentId) {
        return questionAnswerRepository.findQuestionAnswerBy(studentId).orElse(null);
    }

    /**
     * The method forms the complex list that is question and its answers
     * @return List<QuestionAnswerDto>
     */
    // id ,имя вопроса, лист с ответами типа List<Answer>
    public List<QuestionAnswerDto> getAllQuestionWithAnswersList() { // основная задача преобразовать к виду вопрос лист<Ответы>
        List<Question> questionList = questionService.findAllQuestions(); //получаю полный лист вопросов с бд
        List<Answer> answerList = answerService.findAllAnswers();// получаю полный лист ответов
        List<QuestionAnswer> questionAnswerList = findAllAnswersQuestion(); // получаю лист с вопросом и ответом типа 1111 3526

        List<QuestionAnswerDto> questionAnswerDtoSet = new ArrayList<>(); // преобразоываваю к виду вопрос - список ответов начальный
        List<QuestionAnswerDto> finishedQuestionAnswerDtoList = new ArrayList<>();

        for (Long j = 0L; j < questionList.size(); j++) { // лист только с вопросоми = нет ответов пока
            QuestionAnswerDto questionAnswerDto = new QuestionAnswerDto(); // записываю в дто
            questionAnswerDto.setId(questionList.get(Math.toIntExact(j)).getQId());
            questionAnswerDto.setQuestionName(questionList.get(Math.toIntExact(j)).getQuestionName());
            questionAnswerDtoSet.add(questionAnswerDto);   // и записвыаю сюда только имя и id
        }

        for (Long i = 0L; i < questionAnswerDtoSet.size(); i++) { //лист дто с вопросоми пока ответов нет
            List<Long> longList = new ArrayList<>();
            List<Answer> answerList1 = new ArrayList<>(); //лист с ответами ддля отвправки выше

            for (int j = 0; j < questionAnswerList.size(); j++) {  // спсок со всеми вопросами ответами на них бд
                if (questionAnswerDtoSet.get(Math.toIntExact(i)).getId() // иду пол листу только с вопросами если лист вопрос ответ = по id то добавь в лонг все  id ответов
                        .equals(questionAnswerList.get(Math.toIntExact(j)).getQuestionId())) {
                    longList.add(questionAnswerList.get(Math.toIntExact(j)).getAnswerId());
                }
            }
            for (Long k = 0L; k < longList.size(); k++) {  //  полученный id ответов
                for (Long h = 0L; h < answerList.size(); h++) { //сверяю
                    if (longList.get(Math.toIntExact(k)).equals(answerList.get(Math.toIntExact(h)).getId())) {
                        answerList1.add(answerList.get(Math.toIntExact(h)));
                    }
                }
            } // создаю вопрос и список ответов и добавляю в конченый спписок
            QuestionAnswerDto questionAnswerDto = new QuestionAnswerDto((questionAnswerDtoSet
                    .get(Math.toIntExact(i)).getId()), answerList1, questionAnswerDtoSet
                    .get(Math.toIntExact(i)).getQuestionName());

            finishedQuestionAnswerDtoList.add(questionAnswerDto);
        }
        return finishedQuestionAnswerDtoList;
    }

    /**
     * The method forms result of based on chosen on the testing page answers and provides according grade
     * @param formingResult we get that parameter from view
     * @return output
     */
    public String resultProcessing(Map<String, String> formingResult) {
        List<Answer> fullAnswersList = answerService.findAllAnswers();
        Integer correctAnswersCounter = 0;  // здест идет обработка полученной   ытрим мап преобразует каждый элемент потока  в Логнг
        List<Long> idList = formingResult.values().stream().map(f -> Long.parseLong(f)).collect(Collectors.toList());
        for (Answer answer : fullAnswersList) {
            for (Long item : idList) {
                if (answer.getId().equals(item) && answer.isCorrectAnswer()) {  // because long is here
                    correctAnswersCounter++;
                }
            }
        }

        String output = "";
        String englishLevel = "";
        if (correctAnswersCounter < 3) {
            output = "Sorry you score does not let to evaluate your language level. You score is equaled " + correctAnswersCounter.toString();
            englishLevel = "A0";
        } else if (3 <= correctAnswersCounter && correctAnswersCounter < 6) {
            output = "Congratulations! Your level is BEGINNER . You score is equaled " + correctAnswersCounter;
            englishLevel = "A1";
        } else if (6 <= correctAnswersCounter && correctAnswersCounter < 9) {
            output = "Congratulations! Your level is ELEMENTARY . You score is equaled " + correctAnswersCounter;
            englishLevel = "A1";
        } else if (9 <= correctAnswersCounter && correctAnswersCounter < 11) {
            output = "Congratulations! Your level is PRE-intermediate . You score is equaled " + correctAnswersCounter;
            englishLevel = "A2";
        } else if (11 <= correctAnswersCounter && correctAnswersCounter <= 12) {
            output = "Congratulations! Your level is Intermediate . You score is equaled " + correctAnswersCounter;
            englishLevel = "B1";
        }
        Student student = studentService.findAuthenticatedStudent();
        Result result = new Result();
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new java.sql.Timestamp(time);

        result.setScore(correctAnswersCounter);
        result.setTimestamp(timestamp);
        result.setStudent(student);
        result.setResultName(englishLevel);

        resultService.addResultToDataBase(result);
        return output;
    }
}
