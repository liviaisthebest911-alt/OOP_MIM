package Part2.Bank;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public void readData(String fileName) throws IOException {
        try(BufferedReader rd = new BufferedReader(new BufferedReader(new FileReader(fileName)))){
            String line;

            while ((line= rd.readLine())!= null ){
                if(line.isBlank() || line.startsWith("#")) continue;

                if(line.startsWith("CREATE")){
                    int firstQuote = line.indexOf("\"");
                    int lastQuote = line.indexOf("\"");

                    String holderName = line.substring(firstQuote+1,lastQuote);
                    String beforeName = line.substring(0,firstQuote).trim();
                    String[] parts = beforeName.split("\\s+");

                    String afterName = line.substring(lastQuote+1).trim();
                    String[] values = afterName.split("\\s+");

                    String type = parts[1];
                    String accountNumber = parts[2];

                    double balance = Double.parseDouble(values[0]);
                    double extraValue = Double.parseDouble(values[1]);

                    if (type.equalsIgnoreCase("SAVINGS")) {

                        SavingsAccount sa = new SavingsAccount(accountNumber, holderName, balance, extraValue);
                        accounts.add(sa);

                        System.out.println("Đã tạo tài khoản tiết kiệm: " + accountNumber);
                    }  else if (type.equalsIgnoreCase("CURRENT")) {

                        CurrentAccount ca = new CurrentAccount(ccountNumber, holderName, balance, extraValue);

                        accounts.add(ca);

                        System.out.println("Đã tạo tài khoản vãng lai: " + accountNumber);
                    }


                } else if (line.startsWith("DEPOSIT")) {
                    String[] parts = line.split("\\s+");
                    String accountId = parts[1];
                    double amount = Double.parseDouble(parts[2]);

                    Account account = findAccount(accountId);

                    if (account != null) account.deposit(amount);

                    else System.out.println("Không tìm thấy tài khoản: " + accountId);

                } else if (line.startsWith("WITHDRAW")) {
                    String[] parts = line.split("\\s+");
                    String accountId = parts[1];
                    double amount = Double.parseDouble(parts[2]);

                    Account account = findAccount(accountId);

                    if (account != null) {
                        account.withdraw(amount);
                    } else {
                        System.out.println("Không tìm thấy tài khoản: " + accountId);
                    }

                }  else if (line.equals("DISPLAY_ALL")) {

                    displayAllAccounts();

                }

            }
        }
    }
}
