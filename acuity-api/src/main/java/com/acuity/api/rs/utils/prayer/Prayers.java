package com.acuity.api.rs.utils.prayer;

import com.acuity.api.rs.utils.Varps;
import com.acuity.api.rs.wrappers.peers.scene.actors.impl.Player;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Eclipseop.
 * Date: 6/12/2017.
 */
public class Prayers {

	private static final Logger logger = LoggerFactory.getLogger(Prayers.class);

	public static final int VARP_INDEX = 83;

	public static Stream<Prayer> stream() {
		return Arrays.stream(Prayer.values());
	}

	public static List<Prayer> getActive() {
		return stream().filter(Prayer::isActive).collect(Collectors.toList());
	}

	public static int getCurrentVarp() {
		return Varps.get(VARP_INDEX, 0);
	}

	public static int getActiveCount() {
		return Integer.bitCount(getCurrentVarp());
	}

	public static boolean isAnyActivated() {
		return getCurrentVarp() != 0;
	}

	public static boolean isAllDeactivated() {
		return !isAnyActivated();
	}

	public static Optional<Prayer> fromOverhead(final Player player) {
		Preconditions.checkNotNull(player, "Pass in a non null player.");
		final int value = player.getPrayerIcon();
		return stream().filter(p -> p.getOverheadValue() == value).findFirst();
	}
}
