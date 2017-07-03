package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSItemComposite;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class ItemComposite extends CacheableNode{

    private RSItemComposite rsItemComposite;

    @ClientInvoked
    public ItemComposite(RSItemComposite rsItemComposite) {
        super(rsItemComposite);
        this.rsItemComposite = rsItemComposite;
    }

    @NotNull
    public RSItemComposite getRsItemComposite() {
        return rsItemComposite;
    }
}
