package com.acuity.api.rs.peers;

import com.acuity.api.rs.peers.mobile.Npc;
import com.acuity.api.rs.peers.mobile.Player;
import com.acuity.api.rs.query.Npcs;
import com.acuity.rs.api.RSClient;
import com.acuity.rs.api.RSPlayer;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    private RSClient rsClient;

    public Client(@NotNull RSClient peer) {
        Preconditions.checkNotNull(peer);
        this.rsClient = peer;
    }

    public Player[] getPlayers(){
        return Arrays.stream(rsClient.getPlayers())
                .map(peer -> peer != null ? new Player(peer) : null)
                .toArray(Player[]::new);
    }

    public Npc[] getNpcs(){
        return Arrays.stream(rsClient.getNpcs())
                .map(peer -> peer != null ? new Npc(peer) : null)
                .toArray(Npc[]::new);
    }

    public Optional<Player> getLocalPlayer(){
        final RSPlayer localPlayer = rsClient.getLocalPlayer();
        if (localPlayer == null) return Optional.empty();
        return Optional.of(new Player(localPlayer));
    }

    public int getPlane() {
        return rsClient.getPlane();
    }

    public int getBaseSceneX() {
        return rsClient.getBaseScenceX();
    }

    public int getBaseSceneY() {
        return rsClient.getBaseSceneY();
    }

    public int getGameState() {
        return rsClient.getGameState();
    }

    public RSClient getRsClient(){
        return rsClient;
    }
}
