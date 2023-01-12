package ru.netology.settings;

public class Settings {
    private String address;
    private String port;
    private String username;

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }
    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
