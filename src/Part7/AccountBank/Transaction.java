package Part7.AccountBank;

/**
 * CLASS 2: Transaction - Đại diện cho một giao dịch
 *
 * THUỘC TÍNH:
 * - transactionId: String - Mã giao dịch (duy nhất)
 * - transactionType: String - Loại giao dịch (DEPOSIT, WITHDRAW, TRANSFER, INTEREST)
 * - amount: double - Số tiền giao dịch
 * - fromAccount: String - Tài khoản nguồn
 * - toAccount: String - Tài khoản đích
 * - description: String - Mô tả giao dịch
 * - status: String - Trạng thái (SUCCESS, FAILED, PENDING)
 *
 * YÊU CẦU:
 * 1. Implement các interface: Displayable, Validatable
 * 2. Constructor nhận các tham số: transactionId, transactionType, amount, fromAccount, toAccount, description
 *    - status = "SUCCESS"
 * 3. Implement phương thức getDisplayInfo():
 *    - Format: "Transaction [transactionId] | Type: [transactionType] | Amount: [amount] VND | From: [fromAccount] | To: [toAccount] | Status: [status] | Description: [description]"
 * 4. Implement phương thức isValid():
 *    - transactionId không null và không rỗng
 *    - transactionType là một trong: DEPOSIT, WITHDRAW, TRANSFER, INTEREST
 *    - amount > 0
 *    - fromAccount không null và không rỗng
 *    - toAccount không null và không rỗng
 *    - status là một trong: SUCCESS, FAILED, PENDING
 * 5. Phương thức setStatus(String status): set status mới
 * 6. Override toString() trả về getDisplayInfo()
 * 7. Tạo các getter cho tất cả thuộc tính
 */
// ─── Transaction.java ──────────────────────────────────────────────────────────

public class Transaction implements Displayable, Validatable {
    private String transactionId;
    private String transactionType;
    private double amount;
    private String fromAccount;
    private String toAccount;
    private String description;
    private String status;

    public Transaction(String transactionId, String transactionType, double amount,
                       String fromAccount, String toAccount, String description) {
        this.transactionId   = transactionId;
        this.transactionType = transactionType;
        this.amount          = amount;
        this.fromAccount     = fromAccount;
        this.toAccount       = toAccount;
        this.description     = description;
        this.status          = "SUCCESS";
    }

    @Override
    public String getDisplayInfo() {
        return "Transaction "       + transactionId
                + " | Type: "         + transactionType
                + " | Amount: "       + amount + " VND"
                + " | From: "         + fromAccount
                + " | To: "           + toAccount
                + " | Status: "       + status
                + " | Description: "  + description;
    }

    @Override
    public boolean isValid() {
        if (transactionId == null || transactionId.trim().isEmpty())
            return false;
        if (!"DEPOSIT".equals(transactionType)
                && !"WITHDRAW".equals(transactionType)
                && !"TRANSFER".equals(transactionType)
                && !"INTEREST".equals(transactionType))
            return false;
        if (amount <= 0)
            return false;
        if (fromAccount == null || fromAccount.trim().isEmpty())
            return false;
        if (toAccount == null || toAccount.trim().isEmpty())
            return false;
        if (!"SUCCESS".equals(status)
                && !"FAILED".equals(status)
                && !"PENDING".equals(status))
            return false;
        return true;
    }

    public void setStatus(String status) { this.status = status; }

    public String getTransactionId()   { return transactionId;   }
    public String getTransactionType() { return transactionType; }
    public double getAmount()          { return amount;          }
    public String getFromAccount()     { return fromAccount;     }
    public String getToAccount()       { return toAccount;       }
    public String getDescription()     { return description;     }
    public String getStatus()          { return status;          }

    @Override
    public String toString() { return getDisplayInfo(); }
}
