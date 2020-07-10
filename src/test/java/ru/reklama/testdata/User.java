package ru.reklama.testdata;

import static ru.reklama.autotests.BaseTest.properties;

public class User {

    private String login;
    private String password;
    private String pathToDownload;

    public User() {
        this.login = properties.getProperty("login");
        this.password = properties.getProperty("password");
        this.pathToDownload = properties.getProperty("pathToDownload");
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}