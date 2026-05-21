package Part7.AccountBank;

/**
 * Exception khi số dư không đủ
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}