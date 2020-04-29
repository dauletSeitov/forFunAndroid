package kz.lounge.myapplication.model;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String name;
    private String login;
    private String password;
//    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
//
//    public LocalDateTime getUpdated() {
//        return updated;
//    }
//
//    public void setUpdated(LocalDateTime updated) {
//        this.updated = updated;
//    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}