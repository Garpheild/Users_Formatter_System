package org.hse.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для ConsoleUserFormatter")
class ConsoleUserFormatterTest {

    private UserFormatter formatter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        formatter = new UserFormatter();
    }

    @Test
    @DisplayName("Должен форматировать пользователя в строку")
    void shouldFormatUser() {
        // given
        User user = new User(1, "John", "Doe", "john@example.com");
        String expected = "ID: 1 | Name: John Doe | Email: john@example.com";

        // when
        String actual = formatter.format(user);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пользователя с пустыми полями")
    void shouldFormatUserWithEmptyFields() {
        // given
        User user = new User(2, "", "", "");
        String expected = "ID: 2 | Name:   | Email: ";

        // when
        String actual = formatter.format(user);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}