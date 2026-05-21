package Part2.Bank;

/**
 * Lớp CurrentAccount đại diện cho một tài khoản vãng lai.
 * Lớp này kế thừa từ lớp Account và thêm thuộc tính hạn mức thấu chi.
 */
public class CurrentAccount extends Account {
    /**
     * Hạn mức thấu chi, cho phép tài khoản có số dư âm đến một giới hạn nhất định.
     */
    private double overdraftLimit;

    /**
     * Hàm khởi tạo cho CurrentAccount.
     *
     * @param accountNumber     Số tài khoản.
     * @param accountHolderName Tên chủ tài khoản.
     * @param balance           Số dư ban đầu.
     * @param overdraftLimit    Hạn mức thấu chi.
     */
    public CurrentAccount(String accountNumber, String accountHolderName, double balance, double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * [BÀI TẬP] Triển khai phương thức rút tiền cho tài khoản vãng lai.
     * Yêu cầu:
     * 1. Kiểm tra nếu số tiền rút (amount) lớn hơn 0. Nếu không, in ra thông báo:
     *    "Số tiền rút phải lớn hơn 0."
     * 2. Nếu số tiền hợp lệ, kiểm tra xem số tiền rút có vượt quá khả năng chi trả hay không.
     *    Khả năng chi trả = số dư hiện tại + hạn mức thấu chi (`this.balance + this.overdraftLimit`).
     *    - Nếu `amount <= (this.balance + this.overdraftLimit)`, thực hiện rút tiền bằng cách
     *      trừ `amount` khỏi `this.balance` và in ra thông báo thành công:
     *      "Rút thành công [số tiền] từ tài khoản [số tài khoản]".
     *    - Nếu vượt quá, in ra thông báo thất bại: "Rút tiền thất bại. Vượt quá hạn mức thấu chi."
     *
     * @param amount Số tiền cần rút.
     */
    @Override
    public void withdraw(double amount) {
       if(amount > 0) System.out.println("Số tiền rút phải lớn hơn 0.");

       if(amount <= this.balance + this.overdraftLimit) {
           this.balance -= amount;
           System.out.println("Rút thành công "+amount+" từ tài khoản "+accountNumber);
       }
       System.out.println("Phương thức withdraw() của CurrentAccount chưa được triển khai.");
    }

    /**
     * Ghi đè phương thức hiển thị chi tiết để thêm thông tin hạn mức thấu chi.
     */
    @Override
    public void displayDetails() {
        super.displayDetails(); // Gọi phương thức của lớp cha
        System.out.println("Loại tài khoản: Vãng lai");
        System.out.printf("Hạn mức thấu chi: %,.2f VND\n", this.overdraftLimit);
    }
}