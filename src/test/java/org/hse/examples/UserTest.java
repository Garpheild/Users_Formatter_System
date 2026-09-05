package org.hse.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для User")
class UserTest {

    @Test
    @DisplayName("Должен корректно создавать пользователя")
    void shouldCreateUser() {
        // given
        User user = new User(1, "John", "Doe", "john@example.com");

        // then
        assertThat(user.id()).isEqualTo(1);
        assertThat(user.firstName()).isEqualTo("John");
        assertThat(user.lastName()).isEqualTo("Doe");
        assertThat(user.email()).isEqualTo("john@example.com");
    }

    @Test
    @DisplayName("Должен возвращать полное имя")
    void shouldReturnFullName() {
        // given
        User user = new User(1, "John", "Doe", "john@example.com");

        // when
        String fullName = user.getFullName();

        // then
        assertThat(fullName).isEqualTo("John Doe");
    }
}