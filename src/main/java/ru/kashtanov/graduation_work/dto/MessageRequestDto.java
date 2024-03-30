package ru.kashtanov.graduation_work.dto;

/**
 * MessageRequestDto is need for carrying data of every message with unique student ID
 */
public class MessageRequestDto {
    private Integer studentId;
    private String message;


    public MessageRequestDto(Integer studentId, String message) {
        this.studentId = studentId;
        this.message = message;
    }


    public MessageRequestDto() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
