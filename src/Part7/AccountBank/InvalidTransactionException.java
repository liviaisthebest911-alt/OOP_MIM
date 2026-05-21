package Part7.AccountBank;

/**
 * Exception khi giao dịch không hợp lệ
 */
public class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}