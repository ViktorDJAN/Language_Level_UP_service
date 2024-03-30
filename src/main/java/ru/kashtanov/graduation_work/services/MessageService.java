package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.repositories.MessageRepository;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;
import ru.kashtanov.graduation_work.repositories.MessageSenderReceiverRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageService is service class that has a function of getting messages between users
 */
@Service
public class MessageService {
    private final MessageSenderReceiverRepository messageSenderReceiverRepository;
    private final MessageRepository messageRepository;

    public MessageService(MessageSenderReceiverRepository messageSenderReceiverRepository,
                          MessageRepository messageRepository) {
        this.messageSenderReceiverRepository = messageSenderReceiverRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * This method implements getting messages between to users
     * @param senderId it is a parameter of user that is on the sender side
     * @return List<Message> all sent messages between users
     */
    public List<Message> getAllMessagesBetweenSenderAndReceiver(Integer senderId) {
        List<Message> messagesList = messageRepository.findAll();
        List<MessageSenderReceiver> messageSenderReceiverList = messageSenderReceiverRepository
                .findAllMessagesBetweenSenderAndReceiverById(senderId);
        List<Message> finalMessageList = new ArrayList<>();
        for (MessageSenderReceiver msr : messageSenderReceiverList) {
            for (Message message : messagesList) {
                if (msr.getMessage().getMessage_id().equals(message.getMessage_id())) {
                    finalMessageList.add(message);
                }
            }
        }
        return finalMessageList;
    }

    public void addNewMessageToDb(Message message) {
        messageRepository.save(message);
    }
}
