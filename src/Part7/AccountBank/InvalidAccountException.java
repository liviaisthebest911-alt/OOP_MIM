package Part7.AccountBank;

/**
 * Exception khi tài khoản không hợp lệ
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}
