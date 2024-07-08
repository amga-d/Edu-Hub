package service;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.AccountModel;
import model.Instructor;

public interface AccountService {
    
    void saveAccounts();
    void createAccount(Account account);
    List<Instructor> geInstructors();
    Account getAccountByEmail(String email) ;
    Account getAccountById(String Id) ;
    void updateAccount(Account account);
    // void updateAccounts(List<Account> modifiedAccounts);
    void deleteAccount(Account account);
    List<Account> getAllAccounts();
    boolean authenticate(String username, String password);

}