package ru.netology;

import org.yaml.snakeyaml.Yaml;
import ru.netology.controller.Controller;
import ru.netology.repository.Repository;
import ru.netology.service.Service;
import ru.netology.settings.Settings;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) throws IOException {
        createLayers();

        var settings = getSettings();
        var address = settings.getAddress();
        var port = settings.getPort();

        try (var clientSocket = new Socket(address, Integer.parseInt(port))) {

        } catch (IOException e) {
            e.getMessage();
        }




    }

    private static void createLayers() {
        var repository = new Repository();
        var service = new Service(repository);
        var controller = new Controller(service);
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