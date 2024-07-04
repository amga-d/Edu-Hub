package service;
import java.util.List;

import model.Account;

public interface AccountService {
    
    void createAccount(Account account);
    Account getAccountByUsername(String username) throws Exception;
    void updateAccount(Account account);
    void deleteAccount(String username);
    List<Account> getAllAccounts();
    boolean authenticate(String username, String password);
}