import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import users.User;
import users.UserRepository;

import java.util.Optional;

public class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    void checkEmptyUserRepository() {
        Assertions.assertTrue(userRepository.getAllUsers().isEmpty());
    }

    @Test
    void checkFilledUserRepository() {
        userRepository.addUser(new User("u1", "p1"));
        userRepository.addUser(new User("u2", "p2"));
        userRepository.addUser(new User("u3", "p3"));
        Assertions.assertFalse(userRepository.getAllUsers().isEmpty());
    }

    @Test
    void findAddedUserByLogin() {
        Assertions.assertEquals("u1",
                userRepository.findUserByLogin("u1").get().getLogin());
    }

    @Test
    void findMissingUserByLogin() {
        Assertions.assertEquals(Optional.empty(),
                userRepository.findUserByLogin("u4"));
    }

    @Test
    void findAddedUserByLoginAndPasswordWithCorrectValues() {
        Assertions.assertEquals("u1",
                userRepository.findUserByLoginAndPassword("u1", "p1").get().getLogin());
        Assertions.assertEquals("p1",
                userRepository.findUserByLoginAndPassword("u1", "p1").get().getPassword());
    }

    @Test
    void findAddedUserByLoginAndPasswordWithWrongLogin() {
        Assertions.assertEquals(Optional.empty(),
                userRepository.findUserByLoginAndPassword("u4", "p1"));
    }

    @Test
    void findAddedUserByLoginAndPasswordWithWrongPassword() {
        Assertions.assertEquals(Optional.empty(),
                userRepository.findUserByLoginAndPassword("u1", "p2"));
    }

    @Test
    void findMissingUserByLoginAndPassword() {
        Assertions.assertEquals(Optional.empty(),
                userRepository.findUserByLoginAndPassword("u4", "p4"));
    }

}