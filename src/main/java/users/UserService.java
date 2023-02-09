package users;

import exceptions.UserNonUniqueException;

import java.util.ArrayList;
import java.util.List;

public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String login, String password) {
        User user = new User(login, password);
        if (login == null || login.isEmpty() || login.isBlank()) {
            throw new IllegalArgumentException("Поле Login должно быть заполнено");
        }
        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("Поле Password должно быть заполнено");
        }
        boolean userExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(t -> t.equals(user));
        if (userExist) {
            throw new UserNonUniqueException("Пользователь с таким Login уже существует");
        }
        this.userRepository.addUser(user);
    }

    public List<String> getAllLogins() {
        List<String> listOfLogins = new ArrayList<>();
        userRepository.getAllUsers()
                .forEach(user -> listOfLogins.add(user.getLogin()));
        return listOfLogins;
    }
}
