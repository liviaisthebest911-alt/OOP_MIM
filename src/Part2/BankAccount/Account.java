package Part2.BankAccount;

import java.time.temporal.TemporalAmount;

public abstract class Account {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println("Gửi tiền thành công "+amount+" vào tài khoản "+accountNumber);
        }
        System.out.println("Số tiền gửi vào phải lớn hơn 0.");
    }

    public abstract void withdraw(double amount);

    public void displayDetails(){
        System.out.println("------------------------------------");
        System.out.println( "Số tài khoản: "+accountNumber);
        System.out.println("Chủ tài khoản: "+accountHolderName);
        System.out.println(String.format("%,.2f",balance)+" VND");
    }
}
