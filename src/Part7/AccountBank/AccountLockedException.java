package Part7.AccountBank;

/**
 * Exception khi tài khoản bị khóa
 */
public class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}
