package com.acuity.api.rs.wrappers.peers.composite;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.utils.PlayerEquipment;
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

    public PlayerEquipment getEquipmentIDs(){
        return new PlayerEquipment(rsPlayerComposite.getEquipmentIDs());
    }

    public boolean isFemale(){
        return rsPlayerComposite.isFemale();
    }

    @NotNull
    public RSPlayerComposite getRsPlayerComposite() {
        return rsPlayerComposite;
    }
}
