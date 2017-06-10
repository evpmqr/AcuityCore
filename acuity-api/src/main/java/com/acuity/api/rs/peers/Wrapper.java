package com.acuity.api.rs.peers;

import com.google.common.base.Preconditions;

/**
 * Created by Zach on 6/9/2017.
 */
public class Wrapper<T> {

    protected T peer;

    public Wrapper(T peer) {
        this.peer = Preconditions.checkNotNull(peer);
    }

    public T getPeer() {
        return peer;
    }
}
