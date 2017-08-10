package com.acuity.db.domain.vertex.impl;

import com.acuity.db.domain.common.EncryptedString;
import com.acuity.db.domain.vertex.Vertex;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class Proxy extends Vertex{

    private String ownerID;
    private String host;
    private int port;
    private String username;
    private EncryptedString password;

    public Proxy(String ownerID, String host, int port, String username, EncryptedString password) {
        this.ownerID = ownerID;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Proxy() {
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public EncryptedString getPassword() {
        return password;
    }
}
