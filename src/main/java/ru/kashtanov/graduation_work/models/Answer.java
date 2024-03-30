package ru.kashtanov.graduation_work.models;

import javax.persistence.*;

/**
 * Answer is used when forming the test page
 */
@Entity(name = "Answer")
@Table
public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "answer_name", nullable = true, columnDefinition = "TEXT")
    private String answerName;
    @Column(name = "is_correct_answer")
    private boolean isCorrectAnswer;

    public Answer() {
    }

    public Answer(String answerName, boolean isCorrectAnswer) {
        this.answerName = answerName;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Answer(Long id, String answerName) {
        this.id = id;
        this.answerName = answerName;
    }

    public Answer(Long id, String answerName, boolean isCorrectAnswer) {
        this.id = id;
        this.answerName = answerName;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return
                "idAnswer=" + id
                        + ", answerName='" + answerName + '\'' +
                        ", isCorrectAnswer=" + isCorrectAnswer;
    }
}
