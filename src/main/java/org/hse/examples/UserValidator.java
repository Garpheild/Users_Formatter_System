package org.hse.examples;

import java.util.List;

public class UserValidator {

    public void validate(List<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("Пользователь должен быть не нулевым");
        }
        if (users.isEmpty()) {
            throw new IllegalArgumentException("Пользователь должен быть не пустым");
        }

        users.forEach(this::validateUser);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь должен быть не нулевым");
        }
        if (user.firstName() == null || user.firstName().isBlank()) {
            throw new IllegalArgumentException("Имя пользователя пусто: " + user.id());
        }
        if (user.lastName() == null || user.lastName().isBlank()) {
            throw new IllegalArgumentException("Фамилия пользователя пуста: " + user.id());
        }
    }
}