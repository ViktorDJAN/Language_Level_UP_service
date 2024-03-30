package ru.kashtanov.graduation_work.models;

import javax.persistence.*;

/**
 * MessageSenderReceiver alleviates work with messages which are sent by a student and tutor
 */
@Entity // указывает на то что это сущность может быть трансформирована замапина в бд и иметь таблицу там
@Table(name = "messages_sender_receiver")
public class MessageSenderReceiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "sender_id")
    private Integer sender_id;
    @Column(name = "receiver_id")
    private Integer receiver_id;

    @OneToOne
    private Message message;

    public MessageSenderReceiver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
