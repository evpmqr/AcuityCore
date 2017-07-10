package com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSAxisAlignedBoundingBox;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class AxisAllignedBoundingBox extends BoundingBox{

    private RSAxisAlignedBoundingBox rsAxisAlignedBoundingBox;

    @ClientInvoked
    public AxisAllignedBoundingBox(RSAxisAlignedBoundingBox axisAllignedBoundingBox) {
        super(axisAllignedBoundingBox);
        this.rsAxisAlignedBoundingBox = axisAllignedBoundingBox;
    }

    public RSAxisAlignedBoundingBox getRsAxisAlignedBoundingBox() {
        return rsAxisAlignedBoundingBox;
    }
}
