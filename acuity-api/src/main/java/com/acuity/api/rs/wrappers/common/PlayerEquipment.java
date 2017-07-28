package com.acuity.api.rs.wrappers.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eclipseop.
 * Date: 7/4/2017.
 */
public class PlayerEquipment {

	private static final Logger logger = LoggerFactory.getLogger(PlayerEquipment.class);

	public enum Slot {
		HELMET(0),
		CAPE(1),
		NECKLACE(2),
		WEAPON(3),
		CHEST(4),
		SHIELD(5),
		LEGS(7),
		HANDES(9),
		FEET(10);

		private int index;

		Slot(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	//public final static int SLOTS_RING = 12;
	//public final static int SLOTS_AMMO = 13;

	private int[] equipmentIDs;

	public PlayerEquipment(int[] equipmentIDs) {
		this.equipmentIDs = equipmentIDs;
	}

	public int getItemId(Slot slot) {
		int id = this.equipmentIDs[slot.getIndex()];
		if (id > 512) {
			id -= 512;
		}
		return id;
	}
}
