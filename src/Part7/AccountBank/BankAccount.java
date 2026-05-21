package Part7.AccountBank;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASS 1: BankAccount - Đại diện cho một tài khoản ngân hàng
 *
 * THUỘC TÍNH:
 * - accountNumber: String - Số tài khoản (duy nhất, 10 chữ số)
 * - accountHolder: String - Tên chủ tài khoản
 * - balance: double - Số dư tài khoản (>= 0)
 * - accountType: String - Loại tài khoản (SAVINGS, CHECKING, BUSINESS)
 * - interestRate: double - Lãi suất (%/năm)
 * - isLocked: boolean - Trạng thái khóa tài khoản
 * - transactions: java.util.List<Transaction> - Danh sách giao dịch
 * - minimumBalance: double - Số dư tối thiểu (mặc định 50000)
 * - private int count = 0 - biến để generate ID cho transaction
 *
 * YÊU CẦU:
 * 1. Implement các interface: Transactable, Displayable, Validatable, Lockable
 * 2. Constructor nhận các tham số: accountNumber, accountHolder, accountType, initialBalance
 *    - balance = initialBalance
 *    - isLocked = false
 *    - transactions = new ArrayList
 *    - minimumBalance = 50000
 *    - interestRate phụ thuộc accountType:
 *      + SAVINGS: 5.0%
 *      + CHECKING: 0.5%
 *      + BUSINESS: 2.0%
 * 3. Implement phương thức deposit(double amount):
 *    - Kiểm tra isLocked == true thì throw AccountLockedException
 *    - Kiểm tra amount > 0, nếu không throw InvalidTransactionException
 *    - Cộng amount vào balance
 *    - Tạo Transaction mới với type = "DEPOSIT" và thêm vào transactions
 * 4. Implement phương thức withdraw(double amount):
 *    - Kiểm tra isLocked == true thì throw AccountLockedException
 *    - Kiểm tra amount > 0, nếu không throw InvalidTransactionException
 *    - Kiểm tra balance - amount >= minimumBalance, nếu không throw InsufficientBalanceException
 *    - Trừ amount từ balance
 *    - Tạo Transaction mới với type = "WITHDRAW" và thêm vào transactions
 * 5. Implement phương thức getBalance(): trả về balance
 * 6. Phương thức transfer(BankAccount targetAccount, double amount):
 *    - Rút tiền từ tài khoản hiện tại
 *    - Gửi tiền vào targetAccount
 *    - Tạo Transaction với type = "TRANSFER" cho cả 2 tài khoản
 * 7. Phương thức calculateInterest():
 *    - Tính lãi: balance * interestRate / 100
 *    - Trả về số tiền lãi
 * 8. Phương thức applyInterest():
 *    - Cộng lãi vào balance
 *    - Tạo Transaction với type = "INTEREST"
 * 9. Implement phương thức getDisplayInfo():
 *    - Format: "Account: [accountNumber] | Holder: [accountHolder] | Type: [accountType] | Balance: [balance] VND | Interest Rate: [interestRate]% | Status: [Active/Locked]"
 * 10. Implement phương thức isValid():
 *     - accountNumber có độ dài 10 và chỉ chứa số
 *     - accountHolder không null và không rỗng
 *     - balance >= 0
 *     - accountType là một trong: SAVINGS, CHECKING, BUSINESS
 * 11. Implement lock(): set isLocked = true
 * 12. Implement unlock(): set isLocked = false
 * 13. Implement isLocked(): trả về isLocked
 * 14. Phương thức getTransactionHistory(): trả về danh sách transactions
 * 15. Override toString() trả về getDisplayInfo()
 * 16. Tạo các getter cho tất cả thuộc tính
 */
public class BankAccount implements Transactable, Displayable, Validatable, Lockable {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;
    private double interestRate;
    private boolean isLocked;
    private List<Transaction> transactions;
    private double minimumBalance;
    private int count = 0;

    // ─── Constructor ───────────────────────────────────────────────────────────

    public BankAccount(String accountNumber, String accountHolder,
                       String accountType, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.isLocked = false;
        this.transactions = new ArrayList<>();
        this.minimumBalance = 50000;

        switch (accountType) {
            case "SAVINGS":
                this.interestRate = 5.0;
                break;
            case "CHECKING":
                this.interestRate = 0.5;
                break;
            case "BUSINESS":
                this.interestRate = 2.0;
                break;
            default:
                this.interestRate = 0.0;
                break;
        }
    }

    // ─── ID Generator ──────────────────────────────────────────────────────────

    private String generateTransactionId() {
        count++;
        return "TXN" + count;
    }

    @Override
    public void deposit(double amount) throws AccountLockedException, InvalidTransactionException {
        if (isLocked)
            throw new AccountLockedException("Account " + accountNumber + " is locked");
        if (amount <= 0)
            throw new InvalidTransactionException("Deposit amount must be greater than 0");

        balance += amount;
        transactions.add(new Transaction(
                generateTransactionId(), "DEPOSIT", amount,
                accountNumber, accountNumber,
                "Deposit to account"
        ));
    }

    @Override
    public void withdraw(double amount)
            throws AccountLockedException, InvalidTransactionException, InsufficientBalanceException {
        if (isLocked)
            throw new AccountLockedException("Account " + accountNumber + " is locked");
        if (amount <= 0)
            throw new InvalidTransactionException("Withdrawal amount must be greater than 0");
        if (balance - amount < minimumBalance)
            throw new InsufficientBalanceException(
                    "Insufficient balance. Current: " + balance + " VND, Minimum required: " + minimumBalance + " VND");

        balance -= amount;
        transactions.add(new Transaction(
                generateTransactionId(), "WITHDRAW", amount,
                accountNumber, accountNumber,
                "Withdraw from account"
        ));
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        balance -= amount;

        transactions.add(new Transaction(generateTransactionId(), "WITHDRAW", amount, accountNumber, accountNumber, "Withdraw from account"));
        transactions.add(new Transaction(
                generateTransactionId(), "TRANSFER", amount,
                accountNumber, targetAccount.getAccountNumber(),
                "Transfer to " + targetAccount.getAccountNumber()
        ));

        targetAccount.balance += amount;

        targetAccount.transactions.add(new Transaction(
                generateTransactionId(), "TRANSFER", amount,
                accountNumber, targetAccount.getAccountNumber(),
                "Transfer to " + targetAccount.getAccountNumber()
        ));
    }

    public double calculateInterest() {
        return balance * interestRate / 100;
    }

    public void applyInterest() {
        double interest = calculateInterest();
        balance += interest;

        generateTransactionId(); // bỏ qua TXN3

        transactions.add(new Transaction(
                generateTransactionId(), "INTEREST", interest,
                accountNumber, accountNumber,
                "Interest applied at " + interestRate + "%"
        ));
    }

    @Override
    public String getDisplayInfo() {
        return "Account: "+accountNumber
                +" | Holder: "+accountHolder
                +" | Type: "+accountType
                +" |Balance: "+balance+" VND"
                +" | Interest Rate: "+interestRate+"%"
                +" | Status: "+ (isLocked ? "Locked" : "Active");
    }

    @Override
    public boolean isValid() {
        if (accountNumber == null
                || accountNumber.length() != 10
                || !accountNumber.matches("\\d{10}"))
            return false;
        if (accountHolder == null || accountHolder.trim().isEmpty())
            return false;
        if (balance < 0)
            return false;
        if (!"SAVINGS".equals(accountType)
                && !"CHECKING".equals(accountType)
                && !"BUSINESS".equals(accountType))
            return false;
        return true;
    }

    @Override
    public void lock()           { isLocked = true;  }

    @Override
    public void unlock()         { isLocked = false; }

    @Override
    public boolean isLocked()    { return isLocked;  }


    public java.util.List<Transaction> getTransactionHistory() { return transactions; }

    @Override
    public String toString() { return getDisplayInfo(); }

    // ─── Getters ───────────────────────────────────────────────────────────────

    public String getAccountNumber()  { return accountNumber;  }
    public String getAccountHolder()  { return accountHolder;  }
    public String getAccountType()    { return accountType;    }
    public double getInterestRate()   { return interestRate;   }
    public double getMinimumBalance() { return minimumBalance; }
}
