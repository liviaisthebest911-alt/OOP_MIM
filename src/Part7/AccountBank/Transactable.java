package Part7.AccountBank;

/**
 * Interface cho các đối tượng có thể giao dịch
 */
public interface Transactable {
    /**
     * Gửi tiền vào tài khoản
     * @param amount Số tiền gửi
     * @throws InvalidTransactionException nếu số tiền không hợp lệ
     */
    void deposit(double amount) throws InvalidTransactionException, AccountLockedException;

    /**
     * Rút tiền từ tài khoản
     * @param amount Số tiền rút
     * @throws InsufficientBalanceException nếu số dư không đủ
     * @throws InvalidTransactionException nếu số tiền không hợp lệ
     */
    void withdraw(double amount) throws InsufficientBalanceException,
            InvalidTransactionException, AccountLockedException;

    /**
     * Lấy số dư hiện tại
     * @return Số dư
     */
    double getBalance();
}
