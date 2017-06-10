package com.acuity.api.rs.peers;

import com.acuity.api.rs.peers.mobile.Npc;
import com.acuity.api.rs.peers.mobile.Player;
import com.acuity.api.rs.peers.scene.Scene;
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
public class Client<T extends RSClient> extends GameEngine<T>{

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public Client(@NotNull T peer) {
        super(peer);
    }

    public Scene getScene(){
        return new Scene(peer.getSceneGraph());// TODO: 6/9/2017 Check if this can be null when logged out. If it can make this Optional
    }

    public Player[] getPlayers(){
        logger.trace("Wrapping RSPlayer[] from RSClient.");
        return Arrays.stream(peer.getPlayers())
                .map(peer -> peer != null ? new Player(peer) : null)
                .toArray(Player[]::new);
    }

    public Npc[] getNpcs(){
        logger.trace("Wrapping RSNpc[] from RSClient.");
        return Arrays.stream(peer.getNpcs())
                .map(peer -> peer != null ? new Npc(peer) : null)
                .toArray(Npc[]::new);
    }

    public Optional<Player> getLocalPlayer(){
        logger.trace("Wrapping RSPlayer-local from RSClient.");
        final RSPlayer localPlayer = peer.getLocalPlayer();
        if (localPlayer == null) return Optional.empty();
        return Optional.of(new Player(localPlayer));
    }

    public int getPlane() {
        return peer.getPlane();
    }

    public int getBaseSceneX() {
        return peer.getBaseScenceX();
    }

    public int getBaseSceneY() {
        return peer.getBaseSceneY();
    }

    public int getGameState() {
        return peer.getGameState();
    }

    public int[][][] getTileHeights() {
        return peer.getTileHeights();
    }

    public byte[][][] getSceneRenderRules() {
        return peer.getSceneRenderRules();
    }

    public int getCameraX() {
        return peer.getCameraX();
    }

    public int getCameraY() {
        return peer.getCameraY();
    }

    public int getCameraZ() {
        return peer.getCameraZ();
    }

    public int getCameraPitch() {
        return peer.getCameraPitch();
    }

    public int getCameraYaw() {
        return peer.getCameraYaw();
    }

    public int getViewportHeight() {
        return peer.getViewportHeight();
    }

    public int getViewportScale() {
        return peer.getViewportScale();
    }

    public int getViewportWidth() {
        return peer.getViewportWidth();
    }

    public int getMapScale() {
        return peer.getMapScale();
    }

    public int getMapRotation() {
        return peer.getMapRotation();
    }

    public int getMinimapOffset() {
        return peer.getMinimapOffset();
    }

    public boolean isResized() {
        return peer.isResized();
    }
}
