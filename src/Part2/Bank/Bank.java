package Part2.Bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;

    public Bank(){
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Đã thêm tài khoản mới: "+account.getAccountNumber());
    }

    public void displayAllAccounts() {
        System.out.println("\n===== DANH SÁCH TẤT CẢ TÀI KHOẢN =====");
        if(!accounts.isEmpty()){
            for(Account account : accounts){
                account.displayDetails();
            }
        }else{
            System.out.println("Ngân hàng chưa có tài khoản nào.");
        }
        System.out.println("=======================================");
    }

    public Account findAccount(String accountNumber){
        for(Account acc : accounts){
            if(acc.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return acc;
            }
        }
        return null;
    }
}
