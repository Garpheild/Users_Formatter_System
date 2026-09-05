package org.hse.examples;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User(3, "Борис", "Иванов", "boris@example.com"),
                new User(1, "Вова", "Петров", "vova@example.com"),
                new User(2, "Антон", "Сидоров", "arton@example.com")
        );

        UserFormatter formatter = new UserFormatter();
        UserValidator validator = new UserValidator();
        UserSortStrategy sortStrategy = new LastNameSortStrategy();

        UserService service = new UserService(formatter, validator, sortStrategy);

        List<String> result = service.processUsers(users);
        result.forEach(System.out::println);

    }
}