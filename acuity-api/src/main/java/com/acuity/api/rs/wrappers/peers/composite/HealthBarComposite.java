package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSHealthBarComposite;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class HealthBarComposite extends CacheableNode{

    private RSHealthBarComposite rsHealthBarComposite;

    public HealthBarComposite(RSHealthBarComposite rsHealthBarComposite) {
        super(rsHealthBarComposite);
        this.rsHealthBarComposite = rsHealthBarComposite;
    }
}
