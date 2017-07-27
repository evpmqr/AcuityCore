package com.acuity.api.rs.wrappers.peers.scene.actors.impl;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Identifiable;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.FineLocation;
import com.acuity.api.rs.wrappers.peers.scene.actors.Actor;
import com.acuity.api.rs.wrappers.peers.types.NpcType;
import com.acuity.rs.api.RSNPCType;
import com.acuity.rs.api.RSNpc;
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
	    return getDefinition().map(NpcType::getName).orElse(null);
	}

	@Override
	public FineLocation getFineLocation(){
		Integer scale = getDefinition().map(NpcType::getScale).orElse(0);
		return new FineLocation(getRsNpc().getFineX() - scale * 64, getRsNpc().getFineY() - scale * 64, Scene.getPlane());
	}

	@Nullable
	@Override
	public Integer getID() {
        return getDefinition().map(NpcType::getID).orElse(null);
	}

	@Override
	public List<String> getActions() {
        return getDefinition().map(NpcType::getActions).map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<NpcType> getDefinition() {
		return Optional.ofNullable(rsNpc.getType()).map(RSNPCType::getWrapper);
	}

	@NotNull
    public RSNpc getRsNpc() {
        return rsNpc;
    }
}
