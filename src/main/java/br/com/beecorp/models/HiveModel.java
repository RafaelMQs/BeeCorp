package br.com.beecorp.models;

import java.util.Date;

public class HiveModel {


    private Integer hiveId;

    public Integer getHiveId() {
        return hiveId;
    }

    public void setHiveId(Integer hiveId) {
        this.hiveId = hiveId;
    }

    private String hiveCode;
    private String UserID;
    private String hiveWeight;
    private String hiveStatus;
    private Date hiveCreationDate;



    public String getHiveCode() {
        return hiveCode;
    }

    public void setHiveCode(String hiveCode) {
        this.hiveCode = hiveCode;
    }

    public String getUserId() {
        return UserID;
    }

    public void setUserId(String userId) {
        this.UserID = userId;
    }

    public String getHiveWeight() {
        return hiveWeight;
    }

    public void setHiveWeight(String hiveWeight) {
        this.hiveWeight = hiveWeight;
    }

    public String getHiveStatus() {
        return hiveStatus;
    }

    public void setHiveStatus(String hiveStatus) {
        this.hiveStatus = hiveStatus;
    }

    public Date getHiveCreationDate() {
        return hiveCreationDate;
    }

    public void setHiveCreationDate(Date hiveCreationDate) {
        this.hiveCreationDate = hiveCreationDate;
    }

    @Override
    public String toString() {
        return "HiveModel{" +
                "hiveId='" + hiveId + '\'' +
                ", hiveCode='" + hiveCode + '\'' +
                ", userId='" + UserID + '\'' +
                ", hiveWeight='" + hiveWeight + '\'' +
                ", hiveStatus='" + hiveStatus + '\'' +
                ", hiveCreationDate=" + hiveCreationDate +
                '}';
    }
}
