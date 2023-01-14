package ru.netology.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MessageRepository {
    private final File file;

    public MessageRepository(String logPath) {
        file = new File(logPath);
    }

    public void recordMessage(String message) throws IOException {
        var logs = new FileWriter(file, true);
        logs.write(message);
        logs.append("\n");
        logs.close();
    }


}
