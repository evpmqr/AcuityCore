package com.acuity.api.query;

import com.acuity.api.peers.mobile.Player;
import com.acuity.client.Acuity;
import com.acuity.rs.api.RSPlayer;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Eclipseop.
 * Date: 6/9/2017.
 */
public class Players {

	private static Logger logger = LoggerFactory.getLogger(Players.class);

	public static Stream<Player> stream() {
		logger.debug("Building Player stream from client.");
		return Arrays.stream(Acuity.getClient().getPlayers())
				.filter(Objects::nonNull)
				.map(Player::new);
	}

	public static List<Player> getLoaded(final Predicate<? super Player> predicate) {
		return stream()
				.filter(predicate::test)
				.collect(Collectors.toList());
	}

	public static List<Player> getLoaded() {
		return getLoaded(p -> true);
	}

	public static List<Player> get(final String... displayNames) {
		return stream().filter(p -> Arrays.asList(displayNames).contains(p.getName())).collect(Collectors.toList());
	}

	public static Optional<Player> getLocal() {
		final RSPlayer localPlayer = Acuity.getClient().getLocalPlayer();
		Preconditions.checkNotNull(localPlayer);
		return Optional.of(new Player(localPlayer));
	}

	public static Optional<Player> getFirst(final String... displayNames) {
		return get(displayNames).stream().findFirst();
	}
}
