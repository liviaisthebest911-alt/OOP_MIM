package Part2.Bank;

/**
 * Lớp SavingsAccount đại diện cho một tài khoản tiết kiệm.
 * Lớp này kế thừa từ lớp Account và thêm thuộc tính lãi suất.
 * Đây là một ví dụ về nguyên lý Kế thừa (Inheritance).
 */
public class SavingsAccount extends Account {
    /**
     * Lãi suất hàng năm của tài khoản tiết kiệm (ví dụ: 0.05 cho 5%).
     */
    private double interestRate;

    /**
     * Hàm khởi tạo cho SavingsAccount.
     *
     * @param accountNumber     Số tài khoản.
     * @param accountHolderName Tên chủ tài khoản.
     * @param balance           Số dư ban đầu.
     * @param interestRate      Lãi suất.
     */
    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        // Gọi hàm khởi tạo của lớp cha (Account)
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    /**
     * [BÀI TẬP] Triển khai phương thức rút tiền cho tài khoản tiết kiệm.
     * Yêu cầu:
     * 1. Kiểm tra nếu số tiền rút (amount) lớn hơn 0. Nếu không, in ra thông báo:
     *    "Số tiền rút phải lớn hơn 0."
     * 2. Nếu số tiền hợp lệ, kiểm tra xem số dư hiện tại (this.balance) có đủ để rút không.
     *    - Nếu `this.balance >= amount`, thực hiện rút tiền bằng cách trừ `amount` khỏi `this.balance`
     *      và in ra thông báo thành công: "Rút thành công [số tiền] từ tài khoản [số tài khoản]".
     *    - Nếu không đủ, in ra thông báo thất bại: "Rút tiền thất bại. Số dư không đủ."
     *
     * @param amount Số tiền cần rút.
     */
    @Override
    public void withdraw(double amount) {
        // TODO: Học viên cần hoàn thiện phương thức này.
        // Xóa dòng này và viết mã của bạn vào đây.
        if(amount <= 0){
            System.out.println("Số tiền rút phải lớn hơn 0.");
        }else{
            if(this.balance >= amount){
                this.balance -= amount;
                System.out.println("Rút thành công "+amount+" từ tài khoản "+this.accountNumber);
            } else{
                System.out.println("Rút tiền thất bại. Số dư không đủ.");
            }
        }
    }

    /**
     * Ghi đè phương thức hiển thị chi tiết để thêm thông tin lãi suất.
     */
    @Override
    public void displayDetails() {
        super.displayDetails(); // Gọi phương thức của lớp cha để hiển thị thông tin chung
        System.out.printf("Loại tài khoản: Tiết kiệm\n");
        System.out.printf("Lãi suất: %.2f%%\n", this.interestRate * 100);
    }
}