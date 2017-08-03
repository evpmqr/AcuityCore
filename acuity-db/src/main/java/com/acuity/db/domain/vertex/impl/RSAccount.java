package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.vertex.Vertex;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class RSAccount extends Vertex {

    private String email;
    private String ign;
    private String password;

    public RSAccount(String email, String ign, String password) {
        this.email = email;
        this.ign = ign;
        this.password = password;
    }

    public RSAccount() {
    }

    public String getEmail() {
        return email;
    }

    public String getIgn() {
        return ign;
    }

    public String getPassword() {
        return password;
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
