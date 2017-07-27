package com.acuity.api.rs.wrappers.peers.types;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.common.PlayerEquipment;
import com.acuity.rs.api.RSPlayerType;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

/**
 * Created by Zachary Herridge on 6/27/2017.
 */
public class PlayerType {

    private RSPlayerType rsPlayerType;

    @ClientInvoked
    public PlayerType(RSPlayerType rsPlayerType) {
        this.rsPlayerType = Preconditions.checkNotNull(rsPlayerType);
    }

    public PlayerEquipment getEquipmentIDs(){
        return new PlayerEquipment(rsPlayerType.getEquipmentIDs());
    }

    public boolean isFemale(){
        return rsPlayerType.isFemale();
    }

    @NotNull
    public RSPlayerType getRsPlayerType() {
        return rsPlayerType;
    }
}
