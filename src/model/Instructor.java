package model;
public class Instructor extends Account {

    private String fullName;
    private String specializatoin;
    private String password;
    private String email;
    private String instructorId; 
    

    public Instructor(String password, String email, String fullName, String specializatoin) {

        super(fullName, password, email);
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.specializatoin = specializatoin;
        this.instructorId = super.getAccountId();
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getSpecializatoin() {
        return specializatoin;
    }


    public void setSpecializatoin(String specializatoin) {
        this.specializatoin = specializatoin;
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


    public String getInstructorId() {
        return instructorId;
    }

}