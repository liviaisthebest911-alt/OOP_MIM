package Part2.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Bank {

    private List<Account> accounts;

    public Bank(){
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
        System.out.println("Đã thêm tài khoản mới: "+account);
    }

    public void displayAllAccounts(){
        System.out.println("\n===== DANH SÁCH TẤT CẢ TÀI KHOẢN =====");
        if(accounts == null) System.out.println("Ngân hàng chưa có tài khoản nào.");

        for (Account account : accounts){
            account.displayDetails();
        }
        System.out.println("=======================================");
    }

    public Account findAccount(String accountNumber){
        return accounts.stream()
                .filter(m -> m.accountNumber.equalsIgnoreCase(accountNumber))
                .findFirst()
                .orElse(null);
    }




    
}
