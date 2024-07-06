package model;

import javafx.scene.image.Image;

public class Admin extends Account {
    private String role;
    public Admin(String name, String password, String email, String role, String adminId) {
        
        super(name, password, email,"Admin.png","Admin");
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }  
}