package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSPlayerComposite;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class PlayerComposite {

    private RSPlayerComposite rsPlayerComposite;

    @ClientInvoked
    public PlayerComposite(RSPlayerComposite rsPlayerComposite) {
        this.rsPlayerComposite = Preconditions.checkNotNull(rsPlayerComposite);
    }

    public int[] getEquipmentIDs(){// TODO: 6/27/2017 Wrap this in some object for slots and document what slot is what index
        return rsPlayerComposite.getEquipmentIDs();
    }

    public boolean isFemale(){
        return rsPlayerComposite.isFemale();
    }

    @NotNull
    public RSPlayerComposite getRsPlayerComposite() {
        return rsPlayerComposite;
    }
}
