package com.acuity.http.api.acuity_account;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccount {

    private String _id;

    private String displayName;
    private String email;
    private String passwordHash;

    public AcuityAccount(String displayName, String email, String passwordHash) {
        this.displayName = displayName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public AcuityAccount() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "AcuityAccount{" +
                "displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
