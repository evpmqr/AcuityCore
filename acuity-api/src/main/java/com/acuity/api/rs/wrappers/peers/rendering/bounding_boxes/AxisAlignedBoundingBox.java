package com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Clickable;
import com.acuity.api.rs.utils.direct_input.ScreenTarget;
import com.acuity.rs.api.RSAxisAlignedBoundingBox;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class AxisAlignedBoundingBox extends BoundingBox implements Clickable{

    private RSAxisAlignedBoundingBox rsAxisAlignedBoundingBox;

    @ClientInvoked
    public AxisAlignedBoundingBox(RSAxisAlignedBoundingBox AxisAlignedBoundingBox) {
        super(AxisAlignedBoundingBox);
        this.rsAxisAlignedBoundingBox = AxisAlignedBoundingBox;
    }

    public RSAxisAlignedBoundingBox getRsAxisAlignedBoundingBox() {
        return rsAxisAlignedBoundingBox;
    }

    @Override
    public Supplier<Optional<ScreenTarget>> getScreenTargetSupplier() {
        return null;
    }
}
