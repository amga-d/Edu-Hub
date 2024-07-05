package model;
public abstract class Account {
    private String name;
    private String password;
    private String email;
    private String Id;

    private static int countId = 0;

    public Account(String name,String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        countId++;
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

    public String getId() {
        return Id;
    }

    private void intialAccountId() {
        String idstr = name.substring(0, 2) + "@"+countId;
        this.Id = idstr;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAccountId(String Id) {
        this.Id = Id;
    }


    public void setId(String id) {
        Id = id;
    }


    public static int getCountId() {
        return countId;
    }


    public static void setCountId(int countId) {
        Account.countId = countId;
    }

}