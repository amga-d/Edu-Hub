package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends Account {

    private LocalDate birthDate ; 
    private Boolean gender;
    private String specializatoin;
    private List<String> coursesId;

    public Instructor(String password, String email, String fullName,Boolean gender,LocalDate date , String specializatoin,String profilePath) {
        super(fullName, password, email,profilePath, "Mentor");
        this.birthDate = date;
        this.gender = gender;
        this.specializatoin = specializatoin;
        this.coursesId = new ArrayList<>();

    }

    public String getSpecializatoin() {
        return specializatoin;
    }


    public void setSpecializatoin(String specializatoin) {
        this.specializatoin = specializatoin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public List<String> getCoursesId() {
        return coursesId;
    }

    public void addCourse(Course course){
        this.coursesId.add(course.getCourseId());
    }

    public void removeCourse(Course course){
        coursesId.remove(course.getCourseId());
    }
    @Override
    public String toString() {

        return "Instructor{" +  
        "name="+super.getName()+
        ", Id="+super.getId()+
        ", birthDate=" + birthDate +
        ", gender=" + gender +
        ", specializatoin=" + specializatoin +
        ", coursesId=" + coursesId +
        '}';
        
    }

    

}