package users;

import java.util.*;

public class UserRepository {
    private final List<User> userRepository = new ArrayList<>();

//    public UserRepository() {
//    }
//    public UserRepository(User user) {
//        userRepository = new ArrayList<>(List.of(user));
//    }
//    public UserRepository(List<User> users) {
//        userRepository = new ArrayList<>(users);
//    }

    public boolean addUser(User user) {
            return this.userRepository.add(user);
    }

    public Optional<User> findUserByLogin(String login) {

        return userRepository.stream()
                .filter(user -> Objects.equals(user.getLogin(), login))
                .findFirst();
    }

    public Optional<User> findUserByLoginAndPassword(String login,
                                                     String password) {
        return userRepository.stream()
                .filter(user -> Objects.equals(user.getLogin(), login) &&
                        Objects.equals(user.getPassword(), password))
                .findFirst();
    }

    public List<User> getAllUsers() {
        return (this.userRepository);
    }
}
