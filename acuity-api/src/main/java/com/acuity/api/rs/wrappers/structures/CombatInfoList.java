package com.acuity.api.rs.wrappers.structures;

import com.acuity.rs.api.RSCombatInfoList;
import com.acuity.rs.api.RSNode;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class CombatInfoList implements java.lang.Iterable{// TODO: 6/12/2017 Rename class

    private RSCombatInfoList rsCombatInfoList;

    public CombatInfoList(RSCombatInfoList peer) {
        this.rsCombatInfoList = Preconditions.checkNotNull(peer);
    }

    public Optional<Node> getNode(){
        return Optional.ofNullable(rsCombatInfoList.getNode()).map(RSNode::getWrapper);
    }

    @Override
    public Iterator iterator() {
        return rsCombatInfoList.iterator();
    }

    @NotNull
    public RSCombatInfoList getRsCombatInfoList() {
        return rsCombatInfoList;
    }
}
