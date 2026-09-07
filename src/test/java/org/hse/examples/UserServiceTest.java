package org.hse.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Тесты для UserService")
class UserServiceTest {

    private UserFormatter userFormatter;
    private UserValidator userValidator;
    private UserSortStrategy sortStrategy;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userFormatter = mock(UserFormatter.class);
        userValidator = mock(UserValidator.class);
        sortStrategy = mock(UserSortStrategy.class);
        userService = new UserService(userFormatter, userValidator, sortStrategy);
    }

    @Test
    @DisplayName("Должен обрабатывать список пользователей")
    void shouldProcessUsers() {
        // given
        User user1 = new User(1, "John", "Doe", "john@example.com");
        User user2 = new User(2, "Jane", "Smith", "jane@example.com");
        List<User> users = List.of(user1, user2);

        when(sortStrategy.sort(users)).thenReturn(users);
        when(userFormatter.format(user1)).thenReturn("John Doe");
        when(userFormatter.format(user2)).thenReturn("Jane Smith");

        // when
        List<String> result = userService.processUsers(users);

        // then
        assertThat(result).containsExactly("John Doe", "Jane Smith");
        verify(userValidator).validate(users);
        verify(sortStrategy).sort(users);
    }

    @Test
    @DisplayName("Должен возвращать пустой список")
    void shouldReturnEmptyList() {
        // given
        List<User> users = List.of();

        when(sortStrategy.sort(users)).thenReturn(List.of());

        // when
        List<String> result = userService.processUsers(users);

        // then
        assertThat(result).isEmpty();
        verify(userValidator).validate(users);
    }

    @Test
    @DisplayName("Должен вызывать валидацию перед сортировкой")
    void shouldValidateBeforeSorting() {
        // given
        User user = new User(1, "John", "Doe", "john@example.com");
        List<User> users = List.of(user);

        when(sortStrategy.sort(users)).thenReturn(users);

        // when
        userService.processUsers(users);

        // then
        verify(userValidator).validate(users);
        verify(sortStrategy).sort(users);
    }
}