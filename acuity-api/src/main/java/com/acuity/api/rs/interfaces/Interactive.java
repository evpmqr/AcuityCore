package com.acuity.api.rs.interfaces;

import com.acuity.api.rs.utils.ActionResult;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public interface Interactive {

	default List getActions() {
		return Collections.EMPTY_LIST;
	}

	default boolean containsAction(final String action) {
		return getActions(p -> p.toLowerCase().contains(action.toLowerCase())).size() > 0;
	}

	default List<String> getActions(final Predicate<String> predicate) {
		final List<String> actions = getActions();
		if (actions == null || predicate == null) {
			return Collections.EMPTY_LIST;
		}
		return actions.stream()
				.filter(Objects::nonNull)
				.filter(predicate)
				.collect(Collectors.toList());
	}

	default ActionResult interact(String action){
		return ActionResult.FAILURE;// TODO: 6/17/2017 Impl
	}
}
