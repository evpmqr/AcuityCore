package com.acuity.db.arango.monitor.filters.impl;

import com.acuity.db.arango.monitor.ArangoMonitorEvent;
import com.acuity.db.arango.monitor.filters.ArangoMonitorFilter;
import com.acuity.db.domain.vertex.impl.AcuityAccount;

import java.util.Optional;

/**
 * Created by Zachary Herridge on 8/4/2017.
 */
public class MessagePackageFilter implements ArangoMonitorFilter<AcuityAccount> {


    @Override
    public Optional<AcuityAccount> matches(ArangoMonitorEvent event) {
        return null;
    }
}
