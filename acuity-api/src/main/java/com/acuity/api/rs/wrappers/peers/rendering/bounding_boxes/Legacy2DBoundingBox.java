package com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.rs.api.RSLegacy2DBoundingBox;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class Legacy2DBoundingBox  extends BoundingBox{

    private RSLegacy2DBoundingBox rsLegacy2DBoundingBox;

    @ClientInvoked
    public Legacy2DBoundingBox(RSLegacy2DBoundingBox rsLegacy2DBoundingBox) {
        super(rsLegacy2DBoundingBox);
        this.rsLegacy2DBoundingBox = rsLegacy2DBoundingBox;
    }

    public RSLegacy2DBoundingBox getRsLegacy2DBoundingBox() {
        return rsLegacy2DBoundingBox;
    }
}
