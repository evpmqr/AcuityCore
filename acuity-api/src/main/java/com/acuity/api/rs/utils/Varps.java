package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/12/2017.
 */
public class Varps {

	public static final int MAX_VARP = 2000;
	public static final int[] BIT_MASKS = new int[32];

	static {
		int var = 2;
		for (int i = 0; i < 32; i++) {
			BIT_MASKS[i] = var - 1;
			var += var;
		}
	}

	public static Optional<int[]> getAll() {
		return Optional.ofNullable(AcuityInstance.getClient().getVarps());
	}

	public static int get(final int index) {
		Preconditions.checkArgument(index < MAX_VARP && index > 0, "Bad index, min index: 1, max index: 1999");
		final Optional<int[]> optional = getAll();

		if (optional.isPresent()) {
			final int[] varps = optional.get();

			return varps.length == 0 ? -1 : varps[index];
		}
		return -1;
	}

	public static boolean getBoolean(final int index) {
		return get(index) == 1;
	}
}
