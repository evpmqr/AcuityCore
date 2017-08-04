package com.acuity.db.arango.monitor.filters;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public interface ArangoMonitorFilter {

    boolean matches(ArangoMonitorEvent event);

}
