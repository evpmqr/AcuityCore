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
public class Player extends Actor {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

	private RSPlayer rsPlayer;

	public Player(@NotNull final RSPlayer peer) {
		super(peer);
		this.rsPlayer = Preconditions.checkNotNull(peer);
	}

	public boolean isSkulled() {
		return getSkullIcon() == 0;
	}

	public RSPlayerComposite getAppearance() {
		return rsPlayer.getAppearance();// TODO: 6/9/2017 Add wrapper
	}

	public int getCombatLevel() {
		return rsPlayer.getCombatLevel();
	}

	public RSModel getModel() {
		return rsPlayer.getModel(); // TODO: 6/9/2017 Add wrapper
	}

	public int getPrayerIcon() {
		return rsPlayer.getPrayerIcon(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getSkullIcon() {
		return rsPlayer.getSkullIcon(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getTeam() {
		return rsPlayer.getTeam(); // TODO: 6/9/2017 Find the default value and document it
	}

	public int getTotalLevel() {
		return rsPlayer.getTotalLevel();
	}

	@Override
	public List<String> getActions() {
		return Arrays.asList(rsPlayer.getActions());
	}

	@Nullable
	@Override
	public String getName() {
		return rsPlayer.getName();
	}

    public RSPlayer getRsPlayer() {
        return rsPlayer;
    }
}
