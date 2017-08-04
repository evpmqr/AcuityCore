package com.acuity.db.arango.monitor.filters.impl;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;
import com.acuity.db.arango.monitor.filters.ArangoMonitorFilter;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class MessagePackageFilter implements ArangoMonitorFilter {

    @Override
    public boolean matches(ArangoMonitorEvent event) {
        return false;
    }
}
