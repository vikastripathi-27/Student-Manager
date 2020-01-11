package com.nextlink.studentmanager;

public class userProfile{

    public String name_register;
    public String college_register;
    public String semester_register;
    public String branch_register;
    public String rollno_register;
    public String website_register;

    public String getName_register() {
        return name_register;
    }

    public void setName_register(String name_register) {
        this.name_register = name_register;
    }

    public String getCollege_register() {
        return college_register;
    }

    public void setCollege_register(String college_register) {
        this.college_register = college_register;
    }

    public String getSemester_register() {
        return semester_register;
    }

    public void setSemester_register(String semester_register) {
        this.semester_register = semester_register;
    }

    public String getBranch_register() {
        return branch_register;
    }

    public void setBranch_register(String branch_register) {
        this.branch_register = branch_register;
    }

    public String getRollno_register() {
        return rollno_register;
    }

    public void setRollno_register(String rollno_register) {
        this.rollno_register = rollno_register;
    }

    public String getWebsite_register() {
        return website_register;
    }

    public void setWebsite_register(String website_register) {
        this.website_register = website_register;
    }


    public userProfile(String name_register, String college_register, String semester_register, String branch_register, String rollno_register, String website_register) {
        this.name_register = name_register;
        this.college_register = college_register;
        this.semester_register = semester_register;
        this.branch_register = branch_register;
        this.rollno_register = rollno_register;
        this.website_register = website_register;
    }





    public userProfile() {

    }


}
