package model;
public class Admin extends Account {
    private String role;
    private String name;
    private String password;
    private String email;
    private String AdminId;
    public Admin(String name, String password, String email, String role, String adminId) {
        super(name, password, email);
        this.role = role;
        this.name= name;
        this.email=email;
        this.password=password;
        this.AdminId = super.getAccountId();
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public String getAdminId() {
        return AdminId;
    }
    public void setAdminId(String adminId) {
        AdminId = adminId;
    } 

    
    
}