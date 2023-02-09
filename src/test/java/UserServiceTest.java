import exceptions.UserNonUniqueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;
import users.User;
import users.UserRepository;
import users.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final List<User> users = List.of(
            new User("u1", "p1"),
            new User("u2", "p2"),
            new User("u3", "p3")
    );
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    void createUserWhichAbsentInRepository() {
        Mockito.when(this.userRepository.getAllUsers())
                .thenReturn(List.of());
        Mockito.when(this.userRepository.addUser(any(User.class))).thenReturn(true);
        this.userService.createUser("u5", "p5");
        verify(userRepository)
                .addUser(any());
    }

    @Test
    void createUserWhichContainsInRepository() {
        Mockito.when(this.userRepository.getAllUsers())
                .thenReturn(List.of(new User("u5", "p5")));
        Assertions.assertThatThrownBy(() -> this.userService.createUser("u5", "p5"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Пользователь с таким Login уже существует");
    }

    @Test
    void createUserWithIncorrectLogin() {
        Assertions.assertThatThrownBy(() -> userService.createUser("", "p5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Поле Login должно быть заполнено");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void createUserWithIncorrectPassword() {
        Assertions.assertThatThrownBy(() -> userService.createUser("", "p5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Поле Login должно быть заполнено");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void getAllUsersLoginInRepository() {
        Mockito.when(this.userRepository.getAllUsers())
                .thenReturn(List.of());
        this.userService.getAllLogins();
        verify(userRepository, times(1))
                .getAllUsers();
    }


}
