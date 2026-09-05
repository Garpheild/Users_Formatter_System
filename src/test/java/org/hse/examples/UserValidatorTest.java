package org.hse.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Тесты для UserValidator")
class UserValidatorTest {

    private UserValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    @Test
    @DisplayName("Должен пропускать валидный список пользователей")
    void shouldPassValidUsers() {
        // given
        List<User> users = List.of(
                new User(1, "John", "Doe", "john@example.com"),
                new User(2, "Jane", "Smith", "jane@example.com")
        );

        // then
        assertThatCode(() -> validator.validate(users))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при null списке")
    void shouldThrowExceptionOnNullList() {
        // then
        assertThatThrownBy(() -> validator.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Пользователь должен быть не нулевым");
    }

    @Test
    @DisplayName("Должен выбрасывать исключение при пустом списке")
    void shouldThrowExceptionOnEmptyList() {
        // then
        assertThatThrownBy(() -> validator.validate(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Пользователь должен быть не пустым");
    }


    @Test
    @DisplayName("Должен выбрасывать исключение при пустом имени")
    void shouldThrowExceptionOnEmptyFirstName() {
        // given
        List<User> users = List.of(
                new User(1, "", "Doe", "john@example.com")
        );

        // then
        assertThatThrownBy(() -> validator.validate(users))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Имя пользователя пусто: 1");
    }
}