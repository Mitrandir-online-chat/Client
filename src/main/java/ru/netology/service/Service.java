package ru.netology.service;

import ru.netology.repository.MessageRepository;
import ru.netology.settings.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Service {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final MessageRepository messageRepository;

    public Service(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void recordMessageOfClient(String messageOfClient) throws IOException {
        messageRepository.recordMessage(messageOfClient);
    }

    public void initializeClient(Socket clientSocket, Settings settings) throws IOException {
        var toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        var fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        toServer.println(settings.getUsername());
        var greeting = fromServer.readLine();
        System.out.println(greeting);
    }

    public void processMessages(Socket clientSocket) throws IOException {
        var toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        var fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Runnable logic = () -> {
            while (true) {
                String messagesFromClients = null;
                try {
                    messagesFromClients = fromServer.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(messagesFromClients);
                try {
                    recordMessageOfClient(messagesFromClients);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(logic);
        thread.start();
        var scanner = new Scanner(System.in);
        while (true) {
            var messageOfClient = scanner.nextLine();
            toServer.println(messageOfClient);
            System.out.println("введите сообщение");
            if (messageOfClient.equals("/exit")) {
                break;
            }
        }
    }
}
