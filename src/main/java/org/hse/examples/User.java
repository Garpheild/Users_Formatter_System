package org.hse.examples;

public record User(
        int id,
        String firstName,
        String lastName,
        String email
) {
    public String getFullName() {
        return firstName + " " + lastName;
    }
}