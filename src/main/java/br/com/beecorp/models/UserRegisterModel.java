package br.com.beecorp.models;

public class UserRegisterModel {
    private String userName;
    private String userEmail;
    private String userConfirmationEmail;
    private String userPassword;
    private String userConfirmationPassword;
    private String userPhone;
    private String userZipCode;
    private String userAddress;

    /**
     * Getters
     */
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserConfirmationEmail() {
        return userConfirmationEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserConfirmationPassword() {
        return userConfirmationPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public String getUserAddress() {
        return userAddress;
    }

    /**
     * Setters
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserConfirmationEmail(String userConfirmationEmail) {
        this.userConfirmationEmail = userConfirmationEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserConfirmationPassword(String userConfirmationPassword) {
        this.userConfirmationPassword = userConfirmationPassword;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserZipCode(String userZipCode) {
        this.userZipCode = userZipCode;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserRegisterModel{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userConfirmationEmail='" + userConfirmationEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userConfirmationPassword='" + userConfirmationPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userZipCode='" + userZipCode + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
