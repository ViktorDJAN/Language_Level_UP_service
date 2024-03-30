package ru.kashtanov.graduation_work.models;

import javax.persistence.*;

/**
 * QuestionAnswer is used to bind info of questions and theirs answers
 */
@Entity
@Table
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;

    private Long answerId;

    private boolean isCorrectAnswer;

    public QuestionAnswer() {
    }

    public QuestionAnswer(Long questionId, Long answerId, boolean isCorrectAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public QuestionAnswer(Long id, Long questionId, Long answerId, boolean isCorrectAnswer) {
        id = id;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" + "Id=" + id +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                ", isCorrectAnswer=" + isCorrectAnswer +
                '}';
    }
}
