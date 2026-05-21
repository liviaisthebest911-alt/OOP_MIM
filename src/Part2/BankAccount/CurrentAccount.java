package Part2.BankAccount;
/*. Ý nghĩa:

 * - Tài khoản vãng lai có hạn mức thấu chi (overdraftLimit).

 * - Cho phép rút vượt số dư, nhưng không được vượt quá hạn mức thấu chi.

 */
public class CurrentAccount extends Account{
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance, double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= 0){
            System.out.println("Số tiền rút phải lớn hơn 0.");
        } else if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Rút thành công "+amount+" từ tài khoản "+accountNumber);
        }else if (amount > balance + overdraftLimit){
            System.out.println("Rút tiền thất bại. Số dư không đủ.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Loại tài khoản: Vãng lai");
        System.out.println("Hạn mức thấu chi: "+String.format("%,.2f",overdraftLimit)+" VND");
    }
}
