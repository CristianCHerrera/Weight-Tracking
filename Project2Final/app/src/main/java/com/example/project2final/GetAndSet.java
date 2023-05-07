package com.example.project2final;

public class GetAndSet {

    private String userDate;
    private String userWeight;
    private int id;

    // creating getter and setter methods
    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public GetAndSet(String uDate, String uWeight) {
        this.userDate = uDate;
        this.userWeight = uWeight;
    }
}
