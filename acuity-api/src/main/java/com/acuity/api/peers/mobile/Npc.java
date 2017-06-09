package com.acuity.api.peers.mobile;

import com.acuity.rs.api.RSNPC;
import com.acuity.rs.api.RSNPCComposite;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class Npc extends Actor {

	private RSNPC rsNpc;

	public Npc(@NotNull RSNPC peer) {
		super(peer);
        Preconditions.checkNotNull(peer);
        this.rsNpc = peer;
	}

	@Override
	public String getName() {
	    return getDefinition().map(RSNPCComposite::getName).orElse(null);
	}

	@Override
	public Integer getId() {
	    return getDefinition().map(RSNPCComposite::getId).orElse(null);
	}

	@Override
	public List<String> getActions() {
        return getDefinition().map(RSNPCComposite::getActions).map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<RSNPCComposite> getDefinition() {
		return Optional.ofNullable(rsNpc.getDefinition()); // TODO: 6/8/2017 add transform
	}

    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return false;
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }
        };

        System.out.println(predicate);
    }
}
