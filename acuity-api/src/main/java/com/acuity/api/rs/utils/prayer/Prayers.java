package com.acuity.api.rs.utils.prayer;

import com.acuity.api.rs.utils.Varps;
import com.acuity.api.rs.wrappers.scene.mobiles.Player;
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

	public static Stream<Prayer> stream() {
		return Arrays.stream(Prayer.values());
	}

	public static List<Prayer> getActive() {
		return stream().filter(Prayer::isActive).collect(Collectors.toList());
	}

	public static int getVarp() {
		return Varps.get(83, 0);
	}

	public static int getTotalActive() {
		return Integer.bitCount(getVarp());
	}

	public static boolean isAnyActivated() {
		return getVarp() != 0;
	}

	public static boolean isAllDeactivated() {
		return !isAnyActivated();
	}

	public static Optional<Prayer> fromOverhead(final Player player) {
		Preconditions.checkNotNull(player, "Null player");
		final int value = player.getPrayerIcon();
		return stream().filter(p -> p.getOverheadValue() == value).findFirst();
	}
}
