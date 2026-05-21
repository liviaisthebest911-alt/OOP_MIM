package Part7.AccountBank;

import java.util.ArrayList;
import java.util.List;

/**
 * Service quản lý ngân hàng - tuân thủ Single Responsibility Principle
 */
public class BankService {
    private List<BankAccount> accounts;

    public BankService() {
        this.accounts = new ArrayList<>();
    }

    public void createAccount(BankAccount account) throws InvalidAccountException {
        if (!account.isValid()) {
            throw new InvalidAccountException("Invalid account data");
        }

        // Check duplicate account number
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                throw new InvalidAccountException("Account number already exists");
            }
        }

        accounts.add(account);
    }

    public BankAccount findAccountByNumber(String accountNumber) throws InvalidAccountException {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new InvalidAccountException("Account " + accountNumber + " not found");
    }

    public List<BankAccount> getAccountsByType(String accountType) {
        List<BankAccount> filtered = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account.getAccountType().equals(accountType)) {
                filtered.add(account);
            }
        }
        return filtered;
    }

    public List<BankAccount> getAccountsByHolder(String holder) {
        List<BankAccount> filtered = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (account.getAccountHolder().equalsIgnoreCase(holder)) {
                filtered.add(account);
            }
        }
        return filtered;
    }

    public double getTotalBalance() {
        double total = 0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
}
