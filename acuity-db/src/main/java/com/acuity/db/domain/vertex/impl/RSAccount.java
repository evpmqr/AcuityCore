package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;
import com.acuity.security.Encryption;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class RSAccount extends Vertex {

    private String ownerID;
    private String email;
    private String ign;
    private boolean banned;
    private boolean locked;
    private boolean wrongLogin;

    private String passwordIV;
    private String password;

    public RSAccount(String ownerID, String email, String ign, byte[] passwordIV, byte[] password) {
        this.ownerID = ownerID;
        this.email = email;
        this.ign = ign;
        this.passwordIV = Encryption.ENCODER.encode(passwordIV);
        this.password = Encryption.ENCODER.encode(password);
    }

    public RSAccount() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getEmail() {
        return email;
    }

    public String getIgn() {
        return ign;
    }

    public String getPasswordIV() {
        return passwordIV;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBanned() {
        return banned;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isWrongLogin() {
        return wrongLogin;
    }

    @Override
    public String toString() {
        return "RSAccount{" +
                "_key='" + _key + '\'' +
                ", email='" + email + '\'' +
                ", ign='" + ign + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
