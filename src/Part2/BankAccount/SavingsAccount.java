package Part2.BankAccount;

public class SavingsAccount extends  Account{
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= 0){
            System.out.println("Số tiền rút phải lớn hơn 0.");
        } else if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Rút thành công "+amount+" từ tài khoản "+accountNumber);
        }else {
            System.out.println("Rút tiền thất bại. Số dư không đủ.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Loại tài khoản: Tiết kiệm");
        System.out.println( "Lãi suất: "+String.format("%.2f",interestRate*100));
    }
}
