package exceptions;

public class UserNonUniqueException extends RuntimeException{
    public UserNonUniqueException() {
        super();
    }

    public UserNonUniqueException(String message) {
        super(message);
    }
}
