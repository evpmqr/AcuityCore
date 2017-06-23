package com.acuity.db.entities;



/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccount {

    private String _id;

    private String emailAddress;
    private String displayName;
    private String passwordHash;

    public AcuityAccount(String displayName, String email, String passwordHash) {
        this.displayName = displayName;
        this.emailAddress = email;
        this.passwordHash = passwordHash;
    }

    public AcuityAccount() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "AcuityAccount{" +
                "_id='" + _id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
