package ru.netology;

import ru.netology.controller.Controller;
import ru.netology.repository.Repository;
import ru.netology.service.Service;

public class Client {
    public static void main(String[] args) {
        createLayers();

    }

    private static void createLayers() {
        var repository = new Repository();
        var service = new Service(repository);
        var controller = new Controller(service);
    }
}