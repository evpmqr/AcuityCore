package com.acuity.api.rs.peers.mobile;

import com.acuity.api.rs.peers.Client;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSPlayer;
import com.acuity.rs.api.RSPlayerComposite;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Eclipseop.
 * Date: 6/9/2017.
 */
public class Player<T extends RSPlayer> extends Actor<T> {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

	public Player(@NotNull final T peer) {
		super(peer);
	}

	public boolean isSkulled() {
		return getSkullIcon() == 0;
	}

	public RSPlayerComposite getAppearance() {
		return peer.getAppearance();// TODO: 6/9/2017 Add wrapper
	}

	public int getCombatLevel() {
		return peer.getCombatLevel();
	}

	public RSModel getModel() {
		return peer.getModel(); // TODO: 6/9/2017 Add wrapper
	}

	public int getPrayerIcon() {
		return peer.getPrayerIcon(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getSkullIcon() {
		return peer.getSkullIcon(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getTeam() {
		return peer.getTeam(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getTotalLevel() {
		return peer.getTotalLevel();
	}

	@Override
	public List<String> getActions() {
		return Arrays.asList(peer.getActions());
	}

	@Nullable
	@Override
	public String getName() {
		return peer.getName();
	}
}
