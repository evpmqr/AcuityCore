package com.acuity.api.rs.wrappers.peers.scene.actors.accessories;

import com.acuity.api.rs.wrappers.peers.structures.Node;
import com.acuity.rs.api.RSHitUpdate;

/**
 * Created by Zachary Herridge on 7/28/2017.
 */
public class HitUpdate extends Node {

    private RSHitUpdate rsHitUpdate;

    public HitUpdate(RSHitUpdate peer) {
        super(peer);
        this.rsHitUpdate = peer;
    }

    public int getCurrentWidth(){
        return rsHitUpdate.getCurrentWidth();
    }

}
