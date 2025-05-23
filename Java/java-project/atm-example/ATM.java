import java.util.HashMap;

public class ATM {
    private HashMap<String, Account> accounts;
    private Account currentAccount;

    public ATM() {
        accounts = new HashMap<>();
        // 샘플 계정 등록
        accounts.put("user1", new Account("user1", "1234", 1000.0));
        accounts.put("user2", new Account("user2", "5678", 500.0));
    }

    public boolean login(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.verifyPin(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void logout() {
        currentAccount = null;
    }

    public boolean isLoggedIn() {
        return currentAccount != null;
    }

    public double checkBalance() {
        return currentAccount.getBalance();
    }

    public void deposit(double amount) {
        currentAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return currentAccount.withdraw(amount);
    }
}
