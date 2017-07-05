package com.acuity.api.rs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 7/4/2017.
 */
public class PlayerEquipment {

	private static final Logger logger = LoggerFactory.getLogger(PlayerEquipment.class);

	public final static int SLOTS_HELMET = 0;
	public final static int SLOTS_CAPE = 1;
	public final static int SLOTS_NECKLACE = 2;
	public final static int SLOTS_WEAPON = 3;
	public final static int SLOTS_CHEST = 4;
	public final static int SLOTS_SHIELD = 5;
	public final static int SLOTS_LEGS = 7;
	public final static int SLOTS_HANDS = 9;
	public final static int SLOTS_FEET = 10;
	public final static int SLOTS_RING = 12;
	public final static int SLOTS_AMMO = 13;

	private int[] equipment;

	public PlayerEquipment(int[] equipment) {
		this.equipment = equipment;
	}

	public int getItemId(final int slot) {
		int id = this.equipment[slot];
		if (id > 512) {
			id -= 512;
		}
		return id;
	}
}
