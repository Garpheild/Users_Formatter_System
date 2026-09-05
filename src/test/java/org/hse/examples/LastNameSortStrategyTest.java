package org.hse.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты для LastNameSortStrategy")
class LastNameSortStrategyTest {

    private LastNameSortStrategy sortStrategy;

    @BeforeEach
    void setUp() {
        sortStrategy = new LastNameSortStrategy();
    }

    @Test
    @DisplayName("Должен сортировать по фамилии")
    void shouldSortByLastName() {
        // given
        List<User> users = List.of(
                new User(1, "John", "Wilson", "john@example.com"),
                new User(2, "Jane", "Smith", "jane@example.com"),
                new User(3, "Bob", "Brown", "bob@example.com")
        );

        // when
        List<User> sorted = sortStrategy.sort(users);

        // then
        assertThat(sorted).extracting(User::lastName)
                .containsExactly("Brown", "Smith", "Wilson");
    }

    @Test
    @DisplayName("Должен сортировать по имени при одинаковых фамилиях")
    void shouldSortByFirstNameWhenLastNamesEqual() {
        // given
        List<User> users = List.of(
                new User(1, "John", "Smith", "john@example.com"),
                new User(2, "Alice", "Smith", "alice@example.com"),
                new User(3, "Bob", "Smith", "bob@example.com")
        );

        // when
        List<User> sorted = sortStrategy.sort(users);

        // then
        assertThat(sorted).extracting(User::firstName)
                .containsExactly("Alice", "Bob", "John");
    }

    @Test
    @DisplayName("Должен сортировать по ID при одинаковых именах и фамилиях")
    void shouldSortByIdWhenNamesEqual() {
        // given
        List<User> users = List.of(
                new User(3, "John", "Smith", "john3@example.com"),
                new User(1, "John", "Smith", "john1@example.com"),
                new User(2, "John", "Smith", "john2@example.com")
        );

        // when
        List<User> sorted = sortStrategy.sort(users);

        // then
        assertThat(sorted).extracting(User::id)
                .containsExactly(1, 2, 3);
    }
}