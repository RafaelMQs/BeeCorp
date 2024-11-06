package br.com.beecorp.models;

import javax.swing.*;

public class HiveUpdateModel {
    private String WeightHive;
    private String hiveStatusInput;
    private String hiveCodeInput;
    private String UserID;
    private String hiveID;

    @Override
    public String toString() {
        return "HiveUpdateModel{" +
                "hiveID='" + hiveID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", hiveCodeInput='" + hiveCodeInput + '\'' +
                ", hiveStatusInput='" + hiveStatusInput + '\'' +
                ", WeightHive='" + WeightHive + '\'' +
                '}';
    }

    public String getHiveID() {
        return hiveID;
    }

    public void setHiveID(String hiveID) {
        this.hiveID = hiveID;
    }

    public String getHiveCodeInput() {
        return hiveCodeInput;
    }

    public void setHiveCodeInput(String hiveCodeInput) {
        this.hiveCodeInput = hiveCodeInput;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getHiveStatusInput() {
        return hiveStatusInput;
    }

    public void setHiveStatusInput(String hiveStatusInput) {
        this.hiveStatusInput = hiveStatusInput;
    }

    public String getWeightHive() {
        return WeightHive;
    }

    public void setWeightHive(String weightHive) {
        WeightHive = weightHive;
    }
}
