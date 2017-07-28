package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;
import com.acuity.api.rs.wrappers.peers.engine.Varpbit;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by Eclipseop.
 * Date: 6/12/2017.
 */
public class Varps {

	private static final Logger logger = LoggerFactory.getLogger(Varps.class);

	private static final int MAX_VARP = 2000;
	private static final int[] BIT_MASKS = new int[32];

	static {
		int var = 2;
		for (int i = 0; i < 32; i++) {
			BIT_MASKS[i] = var - 1;
			var += var;
		}
	}

	public static int getMask(int index){
	    //Preconditions.checkArgument(index > 0 && index < 32, "Bad mask index, mask index must be in range [0-" + BIT_MASKS.length + ").");
		return BIT_MASKS[index];
	}

	public static Optional<int[]> getAll() {
		Optional<int[]> varps = Optional.ofNullable(AcuityInstance.getClient().getVarps());
		if (varps.map(ints -> ints.length).orElse(0) == 0) return Optional.empty();
		return varps;
	}

	public static int get(final int index, final int defaultValue) {
		Preconditions.checkArgument(index < MAX_VARP && index > 0, "Invalid varp index, index must be in range (0-" + MAX_VARP + ").");
		return getAll()
                .map(ints -> ints[index])
                .orElse(defaultValue);
	}

	public static boolean getBoolean(final int index) {
		return get(index, 0) == 1;
	}

	public static Optional<Varpbit> getVarpBit(int varpbitIndex) {
		return AcuityInstance.getClient().loadVarpbit(varpbitIndex);
	}
}
