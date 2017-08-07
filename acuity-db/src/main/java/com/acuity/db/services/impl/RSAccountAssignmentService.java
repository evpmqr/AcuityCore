package com.acuity.db.services.impl;

import com.acuity.db.AcuityDB;
import com.acuity.db.domain.edge.impl.AssignedTo;
import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.domain.vertex.impl.bot_clients.BotClient;
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

    public static class AssignedToJoined {
        private AssignedTo edge;
        private RSAccount rsAccount;
        private BotClient botClient;

        public AssignedTo getEdge() {
            return edge;
        }

        public RSAccount getRsAccount() {
            return rsAccount;
        }

        public BotClient getBotClient() {
            return botClient;
        }
    }
}
