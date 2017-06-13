package com.acuity.api.rs.utils;

import com.acuity.api.AcuityInstance;

/**
 * Created by Eclipseop.
 * Date: 6/12/2017.
 */
public enum Skill {
	ATTACK,
	DEFENCE,
	STRENGTH,
	HITPOINTS,
	RANGED,
	PRAYER,
	MAGIC,
	COOKING,
	WOODCUTTING,
	FLETCHING,
	FISHING,
	FIREMAKING,
	CRAFTING,
	SMITHING,
	MINING,
	HERBLORE,
	AGILITY,
	THIEVING,
	SLAYER,
	FARMING,
	RUNECRAFTING,
	HUNTER,
	CONSTRUCTION;

	public int getIndex() {
		return ordinal();
	}

	public int getLevel() {
		return AcuityInstance.getClient().getRealSkillLevels()[ordinal()];
	}

	public int getBoostedLevel() {
		return AcuityInstance.getClient().getBoostedSkillLevels()[ordinal()];
	}

	public int getExperience() {
		return AcuityInstance.getClient().getSkillExperiences()[ordinal()];
	}

	@Override
	public String toString() {
		String name = super.name();
		return name.charAt(0) + name.substring(1).toLowerCase();
	}
}
