package ru.netology.controller;


import ru.netology.service.Service;

public class Controller {
    private final Service service;

    public Controller (Service service) {
        this.service = service;
    }
}
