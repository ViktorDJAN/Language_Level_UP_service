package ru.kashtanov.graduation_work.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Message class is used in the chat forming
 */
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;
    private String message;
    private Timestamp timestamp;

    public Message() {
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message1 = (Message) o;
        return Objects.equals(message_id, message1.message_id) && Objects.equals(message, message1.message) && Objects.equals(timestamp, message1.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, message, timestamp);
    }

    @Override
    public String toString() {
        return
                message + ", timestamp=" + timestamp;
    }
}
