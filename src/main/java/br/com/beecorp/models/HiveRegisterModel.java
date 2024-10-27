package br.com.beecorp.models;

public class HiveRegisterModel {
    private String hiveId;
    private String hiveCode;
    private String userId;
    private String hiveWeight;
    private String hiveStatus;

    public String getHiveId() {
        return hiveId;
    }

    public void setHiveId(String hiveId) {
        this.hiveId = hiveId;
    }

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

    @Override
    public String toString() {
        return "HiveRegisterModel{" +
                "hiveId='" + hiveId + '\'' +
                ", hiveCode='" + hiveCode + '\'' +
                ", userId='" + userId + '\'' +
                ", hiveWeight='" + hiveWeight + '\'' +
                ", hiveStatus='" + hiveStatus + '\'' +
                '}';
    }
}

