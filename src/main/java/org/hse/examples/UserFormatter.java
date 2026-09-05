package org.hse.examples;

public class UserFormatter {

    public String format(User user) {
        return String.format("ID: %d | Name: %s | Email: %s",
                user.id(),
                user.getFullName(),
                user.email());
    }
}