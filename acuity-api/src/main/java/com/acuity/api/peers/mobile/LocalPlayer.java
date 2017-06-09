package com.acuity.api.peers.mobile;

import com.acuity.api.interfaces.Locatable;
import com.acuity.api.movement.Tile;
import com.acuity.api.query.Players;
import com.acuity.client.Acuity;

import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/9/2017.
 */
public class LocalPlayer {

	public static Optional<Player> get() {
		if (Acuity.getClient().getGameState() <= 20) {
			return Optional.empty();
		}
		return Players.getLocal();
	}

	public static Tile getLocation() {
		final Optional<Player> player = get();
		return player.map(Locatable::toLocation).orElse(null);
	}

	public static int getX() {
		return getLocation().getX();
	}

	public static int getY() {
		return getLocation().getY();
	}

	public static boolean isAnimating() {
		final Optional<Player> player = get();
		return player.map(Actor::isAnimating).orElse(false);
	}

	public static String getName() {
		final Optional<Player> player = get();
		return player.map(Player::getName).orElse(null);
	}
}
