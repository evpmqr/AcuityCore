package com.acuity.api.query;

import com.acuity.api.interfaces.Locatable;
import com.acuity.api.wrappers.mobile.Npc;
import com.acuity.client.Acuity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public class Npcs {

	private static Logger logger = LoggerFactory.getLogger(Npcs.class);

	public static Stream<Npc> stream() {
		return Arrays.stream(Acuity.getClient().getNpcs())
				.filter(Objects::nonNull)
				.map(Npc::new);
	}

	public static List<Npc> getLoaded(final Predicate<? super Npc> predicate) {
		return stream()
				.filter(predicate::test)
				.collect(Collectors.toList());
	}

	public static List<Npc> getLoaded() {
		return getLoaded(p -> true);
	}

	public static Optional<Npc> getNearest(final Predicate<? super Npc> predicate) {
		return stream().filter(predicate).sorted(Comparator.comparingInt(Locatable::distance)).findFirst();
	}

	public static Optional<Npc> getNearest(final String name) {
		return getNearest(p -> p.getName().equalsIgnoreCase(name));
	}

	public static Optional<Npc> getNearest(final int id) {
		return getNearest(p -> p.getId() == id);
	}

}
