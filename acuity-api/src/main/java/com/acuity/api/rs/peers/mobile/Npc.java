package com.acuity.api.rs.peers.mobile;

import com.acuity.api.rs.interfaces.Identifiable;
import com.acuity.rs.api.RSNPC;
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
public class Npc<T extends RSNPC> extends Actor<T> implements Identifiable {

    private static final Logger logger = LoggerFactory.getLogger(Npc.class);


	public Npc(@NotNull T peer) {
		super(peer);
	}

	@Nullable
	@Override
	public String getName() {
	    return getDefinition().map(RSNPCComposite::getName).orElse(null);
	}

	@Nullable
	@Override
	public Integer getId() {
	    return getDefinition().map(RSNPCComposite::getId).orElse(null);
	}

	@Override
	public List<String> getActions() {
        return getDefinition().map(RSNPCComposite::getActions).map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<RSNPCComposite> getDefinition() {
		return Optional.ofNullable(peer.getDefinition()); // TODO: 6/8/2017 add transform
	}
}
