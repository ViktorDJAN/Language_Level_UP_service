package ru.kashtanov.graduation_work.services;

import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;
import ru.kashtanov.graduation_work.repositories.MessageSenderReceiverRepository;

import java.util.List;

@Service
public class MessageSenderReceiverService {
    private final MessageSenderReceiverRepository messageSenderReceiverRepository;

    public MessageSenderReceiverService(MessageSenderReceiverRepository messageSenderReceiverRepository) {
        this.messageSenderReceiverRepository = messageSenderReceiverRepository;
    }

}
