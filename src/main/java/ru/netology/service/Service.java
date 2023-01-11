package ru.netology.service;

import ru.netology.repository.Repository;

public class Service {

    private final Repository repository;

    public Service (Repository repository) {
        this.repository = repository;
    }
}
