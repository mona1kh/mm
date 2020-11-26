package com.example.tawaqapp;

public class ModelUser {
    String userNameInp, universityNameInp, majorInp, userEmailInp, state, uid;
    public ModelUser(){

    }
    public ModelUser(String userNameInp, String universityNameInp, String majorInp, String userEmailInp, String state, String uid){
        this.userNameInp= userNameInp;
        this.universityNameInp= universityNameInp;
        this.majorInp= majorInp;
        this.userEmailInp= userEmailInp;
        this.state= state;
        this.uid= uid;
    }
    public String getName(){
        return userNameInp;
    }
    public void setName(String userNameInp){
        this.userNameInp = userNameInp;
    }

    public String getEmail(){
        return userEmailInp;
    }
    public void setEmail(String userEmailInp){
        this.userEmailInp = userEmailInp;
    }
    public String getUN(){
        return universityNameInp;
    }
    public void setUN(String universityNameInp){
        this.universityNameInp = universityNameInp;
    }
    public String getMajor(){
        return majorInp;
    }
    public void setMajor(String majorInp){
        this.majorInp = majorInp;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }



}
