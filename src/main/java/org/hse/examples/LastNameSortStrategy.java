package org.hse.examples;

import java.util.Comparator;
import java.util.List;

public class LastNameSortStrategy implements UserSortStrategy {

    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::lastName)
                        .thenComparing(User::firstName)
                        .thenComparing(User::id))
                .toList();
    }
}