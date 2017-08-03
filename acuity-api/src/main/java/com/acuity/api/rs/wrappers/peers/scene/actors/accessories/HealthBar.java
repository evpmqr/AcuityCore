package com.acuity.api.rs.wrappers.peers.scene.actors.accessories;

import com.acuity.api.rs.wrappers.peers.structures.Node;
import com.acuity.api.rs.wrappers.peers.structures.NodeLinkedList;
import com.acuity.rs.api.RSHealthBar;
import com.acuity.rs.api.RSNodeLinkedList;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 7/28/2017.
 */
public class HealthBar extends Node{

    private RSHealthBar rsHealthBar;

    public HealthBar(RSHealthBar peer) {
        super(peer);
        this.rsHealthBar = peer;
    }

    @SuppressWarnings("unchecked")
    public Optional<NodeLinkedList<HitUpdate>> getHitUpdates() {
        return Optional.ofNullable(rsHealthBar.getHitsplats()).map(RSNodeLinkedList::getWrapper);
    }
}
