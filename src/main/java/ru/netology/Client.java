package ru.netology;

import org.yaml.snakeyaml.Yaml;
import ru.netology.repository.MessageRepository;
import ru.netology.service.Service;
import ru.netology.settings.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {


        var settings = getSettings();
        var service = createLayers(settings);
        var address = settings.getAddress();
        var port = settings.getPort();

        try (var clientSocket = new Socket(address, Integer.parseInt(port))) {
            var toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            var fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toServer.println(settings.getUsername());
            var greeting = fromServer.readLine();
            System.out.println(greeting);
            var scanner = new Scanner(System.in);

            while (true) {
                var messageOfClient = scanner.nextLine();
                toServer.println(messageOfClient);
                var messagesFromClients = fromServer.readLine();
                System.out.println(messagesFromClients);
                service.recordMessageOfClient(messagesFromClients);
                System.out.println("введите сообщение");
                if (messageOfClient.equals("/exit")) {
                    break;
                }
            }



        } catch (IOException e) {
            e.getMessage();
        }





    }

    private static Service createLayers(Settings settings) {
        var repository = new MessageRepository(settings.getLogPath());
        var service = new Service(repository);
        return service;
    }

    private static Settings getSettings() throws IOException {
        // TODO надо убрать _dev
        var path = "settings_dev.yml";
        var yaml = new Yaml();
        var in = Files.newInputStream(Paths.get(path));
        Settings settings = yaml.load(in);
        return settings;
    }
}