package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.edge.impl.AssignedTo;
import com.acuity.db.services.DBEdgeCollectionService;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class RSAccountAssignmentService extends DBEdgeCollectionService<AssignedTo> {

    private static final RSAccountAssignmentService INSTANCE = new RSAccountAssignmentService();

    public static RSAccountAssignmentService getInstance() {
        return INSTANCE;
    }

    public RSAccountAssignmentService() {
        super(AcuityDB.DB_NAME, "AssignedTo", AssignedTo.class);
    }

    @Override
    public void insert(AssignedTo entity) {
        removeByFromID(entity.getFrom());
        removeByToID(entity.getTo());
        super.insert(entity);
    }
}
