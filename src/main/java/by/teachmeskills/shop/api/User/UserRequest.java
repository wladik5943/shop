package by.teachmeskills.shop.api.User;

import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;

public class UserRequest {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private UserRole.Role role;

    public UserRole.Role getRole() {
        return role;
    }

    public void setRole(UserRole.Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
