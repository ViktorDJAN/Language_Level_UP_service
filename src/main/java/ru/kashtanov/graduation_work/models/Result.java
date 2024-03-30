package ru.kashtanov.graduation_work.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Result is an entity that forms result after test finishing
 */
@Entity
@Table
public class Result {
    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Integer score;
    private String resultName;

    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @OneToOne
    private Student student;

    public Result() {
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
