package org.hse.examples;

import java.util.List;

public interface UserSortStrategy {
    List<User> sort(List<User> users);
}