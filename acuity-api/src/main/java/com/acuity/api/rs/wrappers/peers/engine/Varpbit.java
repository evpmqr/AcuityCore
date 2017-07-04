package com.acuity.api.rs.wrappers.peers.engine;

import com.acuity.api.rs.utils.Varps;
import com.acuity.api.rs.wrappers.peers.structures.CacheableNode;
import com.acuity.rs.api.RSVarpbit;

/**
 * Created by Zach on 7/4/2017.
 */
public class Varpbit extends CacheableNode{

    private int id;
    private RSVarpbit rsVarpbit;

    public Varpbit(RSVarpbit rsVarpbit) {
        super(rsVarpbit);
        this.rsVarpbit = rsVarpbit;
    }

    public int getLower(){
        return rsVarpbit.getLower();
    }

    public int getUpper(){
        return rsVarpbit.getUpper();
    }

    public int getIndex(){
        return rsVarpbit.getVarpIndex();
    }

    public int getBitCount() {
        return getUpper() - getLower();
    }

    public int getValue() {
        int varpValue = Varps.get(getIndex(), 0);
        return getValue(varpValue);
    }

    public int getValue(int varpValue) {
        int mask = Varps.BIT_MASKS[getUpper() - getLower()];
        return varpValue >> getLower() & mask;
    }

    public int getMask() {
        return Varps.BIT_MASKS[getBitCount()];
    }

    public boolean getBoolean() {
        return isBoolean() && getValue() == 1;
    }

    public boolean isBoolean() {
        return getBitCount() == 1;
    }

    public int getID() {
        return id;
    }

    public Varpbit setID(int id) {
        this.id = id;
        return this;
    }

    public RSVarpbit getRsVarpbit() {
        return rsVarpbit;
    }
}
