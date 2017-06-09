package com.acuity.api.peers.mobile;

import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSPlayer;
import com.acuity.rs.api.RSPlayerComposite;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Eclipseop.
 * Date: 6/9/2017.
 */
public class Player extends Actor {

	private RSPlayer object;

	public Player(@NotNull final RSPlayer object) {
		super(object);
		Preconditions.checkNotNull(object);
		this.object = object;
	}

	public boolean isSkulled() {
		return getSkullIcon() == 0;
	}

	public RSPlayerComposite getAppearance() {
		return object.getAppearance();
	}

	public int getCombatLevel() {
		return object.getCombatLevel();
	}

	public RSModel getModel() {
		return object.getModel();
	}

	public int getPrayerIcon() {
		return object.getPrayerIcon();
	}

	public int getSkullIcon() {
		return object.getSkullIcon();
	}

	public int getTeam() {
		return object.getTeam();
	}

	public int getTotalLevel() {
		return object.getTotalLevel();
	}

	@Override
	public List<String> getActions() {
		return Arrays.asList(object.getActions());
	}

	@Override
	public String getName() {
		return object.getName();
	}
}
