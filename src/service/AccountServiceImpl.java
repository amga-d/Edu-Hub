package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.io.path.Path;

import model.Account;
import model.AccountModel;
import model.Course;
import model.Instructor;
import model.User;

public class AccountServiceImpl implements AccountService {
    
    private List<Account> accountList;
    private AccountModel accountModel;

    public AccountServiceImpl() {
        accountModel = new AccountModel();
        accountList = accountModel.loadAccounts();
        updateCountId();
    }
  
    @Override
    public void saveAccounts(){
        accountModel.saveAccounts(accountList);
    }


    public void updateCountId() {
        if (!accountList.isEmpty()) {
            String idstr = (accountList.get(accountList.size() - 1).getId());
            int indx = idstr.indexOf("@");
            int id = Integer.parseInt(idstr.substring(indx + 1));
            Account.setCountId(id);
        }

    }

    @Override
    public void createAccount(Account account) {
        if (!accountList.contains(account)) {
            accountList.add(account);
            accountModel.saveAccounts(accountList);
        }
        // accountModel.cleanAndSaveAccounts(accountList);

    }

    @Override
    public Account getAccountByEmail(String email) {
        for (Account account : accountList) {
            if (account.getEmail().equals(email)) {
                return account;
            }
        }
        return null; // Account not found
    }

    public Account getAccountById(String id) {
        for (Account account : accountList) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null; // Account not found
    }


    @Override
    public void updateAccount(Account account) {
        // Find the account in the list and update its properties
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getId().equals(account.getId())) {
                accountList.set(i, account);
                break;
            }
            saveAccounts();
        }
    }

    // private void updateAccountWithoutSaving(Account account){
    //     for (int i = 0; i < accountList.size(); i++) {
    //         if (accountList.get(i).getId().equals(account.getId())) {
    //             accountList.set(i, account);
    //             break;
    //         }
            
    //     }
    // }
    
    // @Override
    // public void updateAccounts(List<Account> modifiedAccounts){
    //     for (Account account : modifiedAccounts) {
    //         updateAccountWithoutSaving(account);
    //     }
    //     saveAccounts();
    // }
    

    
    private void deleteAccount(Account account) {
        if (account instanceof User) {
            accountList.remove(account);
            saveAccounts();
        }
    // // Remove the account from the list based on username
    // accountList.removeIf(account -> account.getEmail().equals(username));
    // // Optionally, perform additional logic such as deleting from a database
    }
    @Override
     public void deleteUserAccount(User user) {


        // Remove the user from the registered courses
        CourseService courseService = new CourseServiceImpl(this);
        courseService.deletUserCourses(user);
       

        // Remove the user from the database or storage
        deleteAccount(user);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountList); // Return a copy of the list to prevent direct modification
    }

    public boolean authenticate(String email, String password) {
        Account account = getAccountByEmail(email);
        return account != null && account.getPassword().equals(password);
    }

    @Override
    public List<Instructor> geInstructors() {
        List<Instructor> instructors = new ArrayList<Instructor>();
        for (Account account : accountList) {
            if (account instanceof Instructor) {
                instructors.add((Instructor) account);
                }
        }
        return instructors;
    }
    
}
