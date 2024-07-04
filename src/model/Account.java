package model;
public abstract class Account {
    private String name;
    private String password;
    private String email;
    private String AccountId;

    private static int id = 0;

    public Account(String name,String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        id++;
        intialAccountId();
        
    }
    

    // Getters and Setters


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountId() {
        return AccountId;
    }

    private void intialAccountId() {
        String idstr = name.substring(0, 2) + "@"+id;
        this.AccountId = idstr;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Account.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

}