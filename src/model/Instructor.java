package model;
public class Instructor extends Account {

    private String specializatoin;
    public Instructor(String password, String email, String fullName, String specializatoin) {

        super(fullName, password, email);
        this.specializatoin = specializatoin;
    }

    public String getSpecializatoin() {
        return specializatoin;
    }


    public void setSpecializatoin(String specializatoin) {
        this.specializatoin = specializatoin;
    }

}