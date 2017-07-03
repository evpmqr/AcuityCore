package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSHealthBarComposite;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class HealthBarComposite extends CacheableNode{

    private RSHealthBarComposite rsHealthBarComposite;

    @ClientInvoked
    public HealthBarComposite(RSHealthBarComposite rsHealthBarComposite) {
        super(rsHealthBarComposite);
        this.rsHealthBarComposite = rsHealthBarComposite;
    }

    public int getScale(){
        return rsHealthBarComposite.getHealthScale();
    }

    @NotNull
    public RSHealthBarComposite getRsHealthBarComposite() {
        return rsHealthBarComposite;
    }
}
