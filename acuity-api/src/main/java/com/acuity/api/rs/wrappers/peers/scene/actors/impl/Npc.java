package com.acuity.api.rs.wrappers.peers.scene.actors.impl;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Identifiable;
import com.acuity.api.rs.wrappers.peers.composite.NpcComposite;
import com.acuity.api.rs.wrappers.peers.scene.actors.Actor;
import com.acuity.rs.api.RSNpc;
import com.acuity.rs.api.RSNPCComposite;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class Npc extends Actor implements Identifiable {

    private static final Logger logger = LoggerFactory.getLogger(Npc.class);

	private RSNpc rsNpc;

	@ClientInvoked
	public Npc(@NotNull RSNpc peer) {
		super(peer);
        this.rsNpc = Preconditions.checkNotNull(peer);
	}

	@Nullable
	@Override
	public String getName() {
	    return getDefinition().map(NpcComposite::getName).orElse(null);
	}

	@Nullable
	@Override
	public Integer getID() {// TODO: 6/12/2017 Rename field ID
        return getDefinition().map(NpcComposite::getID).orElse(null);
	}

	@Override
	public List<String> getActions() {
        return getDefinition().map(NpcComposite::getActions).map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<NpcComposite> getDefinition() {
		return Optional.ofNullable(rsNpc.getDefinition()).map(RSNPCComposite::getWrapper);
	}

	@NotNull
    public RSNpc getRsNpc() {
        return rsNpc;
    }
}
