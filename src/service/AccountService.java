package service;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.AccountModel;
import model.Instructor;
import model.User;

public interface AccountService {
    
    void saveAccounts();
    void createAccount(Account account);
    List<Instructor> geInstructors();
    Account getAccountByEmail(String email) ;
    Account getAccountById(String Id) ;
    void updateAccount(Account account);
    // void updateAccounts(List<Account> modifiedAccounts);
    void deleteUserAccount(User user);
    List<Account> getAllAccounts();
    boolean authenticate(String username, String password);

}