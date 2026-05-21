package Part2.Bank;

/**
 * Lớp trừu tượng Account đại diện cho một tài khoản ngân hàng chung.
 * Lớp này chứa các thuộc tính và phương thức cơ bản mà mọi tài khoản đều có.
 * Nó sử dụng nguyên lý Trừu tượng (Abstraction) và Đóng gói (Encapsulation).
 */
public abstract class Account {
    protected String accountNumber;
    protected String accountHolderName;   //tên chủ tài
    protected double balance;  //số dư hiện tại

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Gửi tiền thành công " + amount + " vào tài khoản " + this.accountNumber);
        } else {
            System.out.println("Số tiền gửi vào phải lớn hơn 0.");
        }
    }

    /**
     * Phương thức trừu tượng để rút tiền từ tài khoản.
     * Các lớp con (subclass) phải triển khai (implement) phương thức này
     * vì logic rút tiền khác nhau cho mỗi loại tài khoản.
     *
     * @param amount Số tiền cần rút.
     */
    public abstract void withdraw(double amount);


    /**
     * Phương thức để hiển thị thông tin chi tiết của tài khoản.
     * Cung cấp một cách chuẩn để in thông tin tài khoản.
     */
    public void displayDetails() {
        System.out.println("------------------------------------");
        System.out.println("Số tài khoản: " + this.accountNumber);
        System.out.println("Chủ tài khoản: " + this.accountHolderName);
        System.out.printf("Số dư: %,.2f VND\n", this.balance);
    }

    // Các phương thức Getter để truy cập thông tin (Encapsulation)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }


}