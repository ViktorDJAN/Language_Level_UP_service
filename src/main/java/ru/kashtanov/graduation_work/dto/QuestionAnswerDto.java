package ru.kashtanov.graduation_work.dto;

import ru.kashtanov.graduation_work.models.Answer;

import java.util.List;

/**
 * QuestionAnswerDto mainly need for storing data for question and its answers list
 */
public class QuestionAnswerDto {
    private Long id;
    private List<Answer> answersList;
    private String questionName;

    public QuestionAnswerDto() {
    }

    public QuestionAnswerDto(Long id, List<Answer> answerIdList) {
        this.id = id;
        this.answersList = answerIdList;
    }

    public QuestionAnswerDto(Long id, List<Answer> answersList, String questionName) {
        this.id = id;
        this.answersList = answersList;
        this.questionName = questionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answer> answerIdList) {
        this.answersList = answerIdList;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public String toString() {
        return "QA{"
                + "id=" + id
                + ", answersList=" + answersList +
                ", questionName='" + questionName + '\''
                +'}';
    }
}
