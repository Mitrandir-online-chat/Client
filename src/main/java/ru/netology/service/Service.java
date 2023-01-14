package ru.netology.service;

import ru.netology.repository.MessageRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Service {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final MessageRepository messageRepository;

    public Service (MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void recordMessageOfClient (String messageOfClient) throws IOException {
        messageRepository.recordMessage(messageOfClient);
    }
}
