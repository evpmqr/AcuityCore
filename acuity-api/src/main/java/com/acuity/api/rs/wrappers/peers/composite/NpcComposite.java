package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSNPCComposite;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class NpcComposite extends CacheableNode {

    private RSNPCComposite rsnpcComposite;

    @ClientInvoked
    public NpcComposite(RSNPCComposite rsnpcComposite) {
        super(rsnpcComposite);
        this.rsnpcComposite = rsnpcComposite;
    }

    public String getName() {
        return rsnpcComposite.getName();
    }

    public int getID(){
        return rsnpcComposite.getID();
    }

    public String[] getActions(){
        return rsnpcComposite.getActions();
    }

    @NotNull
    public RSNPCComposite getRsNpcComposite() {
        return rsnpcComposite;
    }
}
