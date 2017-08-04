package com.acuity.db.arango.monitor.filters;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public interface ArangoMonitorFilter<T> {

    Optional<T> matches(ArangoMonitorEvent event);

}
