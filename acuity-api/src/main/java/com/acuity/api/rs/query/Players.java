package com.acuity.api.rs.query;

import com.acuity.api.RSInstance;
import com.acuity.api.rs.peers.mobile.Player;
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

	public static Stream<Player> streamLoaded() {
		logger.debug("Building Player streamLoaded from client.");
		return Arrays.stream(RSInstance.getClient().getPlayers())
				.filter(Objects::nonNull);
	}

	public static List<Player> getLoaded(final Predicate<? super Player> predicate) {
		return streamLoaded()
				.filter(predicate::test)
				.collect(Collectors.toList());
	}

	public static List<Player> getLoaded() {
		return getLoaded(p -> true);
	}

	public static List<Player> get(final String... displayNames) {
		return streamLoaded().filter(p -> Arrays.asList(displayNames).contains(p.getName())).collect(Collectors.toList());
	}

	public static Optional<Player> getLocal() {
	    return RSInstance.getClient().getLocalPlayer();
	}

	public static Optional<Player> getFirst(final String... displayNames) {
		return get(displayNames).stream().findFirst();
	}
}
