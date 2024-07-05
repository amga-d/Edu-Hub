package model;
public class Admin extends Account {
    private String role;
    public Admin(String name, String password, String email, String role, String adminId) {
        super(name, password, email);
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }  
}