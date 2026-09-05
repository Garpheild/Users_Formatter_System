package org.hse.examples;

import java.util.List;

public class UserService {

    private final UserFormatter userFormatter;
    private final UserValidator userValidator;
    private final UserSortStrategy sortStrategy;

    public UserService(UserFormatter userFormatter,
                       UserValidator userValidator,
                       UserSortStrategy sortStrategy) {
        this.userFormatter = userFormatter;
        this.userValidator = userValidator;
        this.sortStrategy = sortStrategy;
    }

    public List<String> processUsers(List<User> users) {
        userValidator.validate(users);

        List<User> sortedUsers = sortStrategy.sort(users);

        return sortedUsers.stream()
                .map(userFormatter::format)
                .toList();
    }
}