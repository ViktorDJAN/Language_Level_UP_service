package ru.kashtanov.graduation_work.dto;

/**
 * TutorForStudentDto is need for storing data of Tutor and Student and passing them
 */
public class TutorForStudentDto {
    private Integer tutorId;
    private String tutorName;
    private Integer studentId;
    private String studentName;

    public TutorForStudentDto(Integer tutorId, String tutorName, Integer studentId, String studentName) {
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public TutorForStudentDto() {
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
