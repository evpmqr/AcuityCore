package com.acuity.rs.api;

/**
 * Created by Zachary Herridge on 6/2/2017.
 */

public interface RSClient {

    int getGameState();

    String getUsername();

    void setUsername(String username);

    RSNPC[] getNpcs();


}
