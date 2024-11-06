package br.com.beecorp.models;

public class HiveRegisterModel {
    private String hiveCode;
    private String userId;
    private Double hiveWeight;
    private String hiveStatus;

    public String getHiveCode() {
        return hiveCode;
    }

    public void setHiveCode(String hiveCode) {
        this.hiveCode = hiveCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getHiveWeight() {
        return hiveWeight;
    }

    public void setWeightHive(Double hiveWeight) {
        this.hiveWeight = hiveWeight;
    }

    public String getHiveStatus() {
        return hiveStatus;
    }

    public void setHiveStatus(String hiveStatus) {
        this.hiveStatus = hiveStatus;
    }

    @Override
    public String toString() {
        return "HiveRegisterModel{" +
                "hiveCode='" + hiveCode + '\'' +
                ", userId='" + userId + '\'' +
                ", hiveWeight='" + hiveWeight + '\'' +
                ", hiveStatus='" + hiveStatus + '\'' +
                '}';
    }
}

