package com.acuity.api.rs.wrappers.peers.engine;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.interfaces.InterfaceComponent;
import com.acuity.api.rs.wrappers.peers.scene.Scene;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Npc;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Player;
import com.acuity.api.rs.wrappers.peers.structures.HashTable;
import com.acuity.rs.api.*;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class Client extends GameEngine {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private final RSClient rsClient;

    @ClientInvoked
    public Client(@NotNull RSClient peer) {
        super(peer);
        this.rsClient = Preconditions.checkNotNull(peer);
    }

    //init in loading screen, could technically be null before then
    public Optional<Scene> getScene(){
        return Optional.ofNullable(rsClient.getSceneGraph()).map(RSScene::getWrapper);
    }

    public Player[] getPlayers(){
        logger.trace("Wrapping RSPlayer[] from RSClient.");
        return Arrays.stream(rsClient.getPlayers())
                .map(peer -> peer != null ? peer.getWrapper() : null)
                .toArray(Player[]::new);
    }

    public Npc[] getNpcs(){
        logger.trace("Wrapping RSNpc[] from RSClient.");
        return Arrays.stream(rsClient.getNpcs())
                .map(peer -> peer != null ? peer.getWrapper() : null)
                .toArray(Npc[]::new);
    }

    public Optional<Player> getLocalPlayer(){
        logger.trace("Wrapping RSPlayer-local from RSClient.");
        return Optional.ofNullable(rsClient.getLocalPlayer()).map(RSPlayer::getWrapper);
    }

    public InterfaceComponent[][] getInterfaces() {
        logger.trace("Wrapping RSInterfaceComponent[][] from RSClient.");
        return Arrays.stream(rsClient.getInterfaces())
                .map(rsInterfaceComponents -> Arrays.stream(rsInterfaceComponents)
                        .map(peer -> peer != null ? peer.getWrapper() : null)
                        .toArray(InterfaceComponent[]::new)
                ).toArray(InterfaceComponent[][]::new);
    }

    public Optional<HashTable> getInterfaceNodeTable() {
        return Optional.ofNullable(rsClient.getInterfaceNodes()).map(RSHashTable::getWrapper);
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

    public int[][][] getTileHeights() {
        return rsClient.getTileHeights();// TODO: 6/12/2017 Can this be null?
    }

    public byte[][][] getSceneRenderRules() {// TODO: 6/12/2017 Can this be null?
        return rsClient.getSceneRenderRules();
    }

    public int getCameraX() {
        return rsClient.getCameraX();
    }

    public int getCameraY() {
        return rsClient.getCameraY();
    }

    public int getCameraZ() {
        return rsClient.getCameraZ();
    }

    public int getCameraPitch() {
        return rsClient.getCameraPitch();
    }

    public int getCameraYaw() {
        return rsClient.getCameraYaw();
    }

    public int getViewportHeight() {
        return rsClient.getViewportHeight();
    }

    public int getViewportScale() {
        return rsClient.getViewportScale();
    }

    public int getViewportWidth() {
        return rsClient.getViewportWidth();
    }

    public int getMapScale() {
        return rsClient.getMapScale();
    }

    public int getMapRotation() {
        return rsClient.getMapRotation();
    }

    public int getMinimapOffset() {
        return rsClient.getMinimapOffset();
    }

    public boolean isResized() {
        return rsClient.isResized();
    }

    public void setUsername(String username){
        //rsClient.setUsername(username);
    }

    public void setPassword(String password){
       // rsClient.setPassword(password);
    }

    public void setLoginIndex(int index){
        //rsClient.setLoginIndex(index);
    }

    public String getLoginResponse1() {
        return rsClient.getLoginResponse1();
    }

    public String getLoginResponse2() {
        return rsClient.getLoginResponse2();
    }

    public String getLoginResponse3() {
        return rsClient.getLoginResponse3();
    }

    public int getLoginIndex() {
        return rsClient.getLoginIndex();
    }

    public int getLoginState() {
        return rsClient.getLoginState();
    }

    public int[] getVarps() {
        return rsClient.getVarps();
    }

    public int[] getRealSkillLevels() {
        return rsClient.getRealSkillLevels();
    }

    public int[] getBoostedSkillLevels() {
        return rsClient.getBoostedSkillLevels();
    }

    public int[] getSkillExperiences() {
        return rsClient.getSkillExperiences();
    }

    @NotNull
    public RSClient getRsClient(){
        return rsClient;
    }
}
