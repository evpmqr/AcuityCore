package com.acuity.api.rs.wrappers.peers.scene.actors.impl;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.peers.composite.PlayerComposite;
import com.acuity.api.rs.wrappers.peers.scene.actors.Actor;
import com.acuity.rs.api.RSPlayer;
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

	@ClientInvoked
	public Player(@NotNull final RSPlayer peer) {
		super(peer);
		this.rsPlayer = Preconditions.checkNotNull(peer);
	}

	public boolean isSkulled() {
		return getSkullIcon() == 0;
	}

	public PlayerComposite getAppearance() {
		return rsPlayer.getAppearance().getWrapper();
	}

	public int getCombatLevel() {
		return rsPlayer.getCombatLevel();
	}

	/*
	default = -1
	magic = 2
	ranged = 1
	melee = 0
	retribution = 3
	redemption = 5
	smite = 4
	 */
	public int getPrayerIcon() {
		return rsPlayer.getPrayerIcon();
	}

	//default value = -1
	public int getSkullIcon() {
		return rsPlayer.getSkullIcon();
	}

	//default value = 0
	public int getTeam() {
		return rsPlayer.getTeam();
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

	@NotNull
    public RSPlayer getRsPlayer() {
        return rsPlayer;
    }
}
