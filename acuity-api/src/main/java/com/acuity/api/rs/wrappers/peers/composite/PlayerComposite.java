package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.rs.api.RSPlayerComposite;
import com.google.common.base.Preconditions;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class PlayerComposite {

    private RSPlayerComposite rsPlayerComposite;

    public PlayerComposite(RSPlayerComposite rsPlayerComposite) {
        this.rsPlayerComposite = Preconditions.checkNotNull(rsPlayerComposite);
    }

    public int[] getEquipmentIDs(){// TODO: 6/27/2017 Wrap this in some object for slots and document what slot is what index
        return rsPlayerComposite.getEquipmentIds();
    }

    public boolean isFemale(){
        return rsPlayerComposite.isFemale();
    }

    public RSPlayerComposite getRsPlayerComposite() {
        return rsPlayerComposite;
    }
}
