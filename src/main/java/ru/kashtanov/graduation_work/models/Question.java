package ru.kashtanov.graduation_work.models;

import javax.persistence.*;

/**
 * Question is used when forming the test page
 */
@Entity(name = "Question")
@Table
public class Question {
    @javax.persistence.Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    @Column(name = "q_id")
    private Long qId;
    @Column(name = "question_name", nullable = false)
    private String questionName;

    public Question() {
    }

    public Question(String questionName) {
        this.questionName = questionName;
    }

    public Question(Long id, String questionName) {
        this.qId = id;
        this.questionName = questionName;
    }

    public Long getQId() {
        return qId;
    }

    public void setQId(Long id) {
        this.qId = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + qId +
                ", questionName='" + questionName + '\'' +  '}';
    }
}
