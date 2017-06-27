package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSItemComposite;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class ItemComposite extends CacheableNode{

    private RSItemComposite rsItemComposite;

    public ItemComposite(RSItemComposite rsItemComposite) {
        super(rsItemComposite);
        this.rsItemComposite = rsItemComposite;
    }
}
