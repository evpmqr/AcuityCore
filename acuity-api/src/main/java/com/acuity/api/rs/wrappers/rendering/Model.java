package com.acuity.api.rs.wrappers.rendering;

import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import com.google.common.base.Preconditions;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class Model extends Renderable {

    private RSModel rsModel;

    public Model(RSModel peer) {
        super(peer);
        this.rsModel = Preconditions.checkNotNull(peer);
    }

    int[] getVerticesX(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getVerticesX();
    }

    int[] getVerticesY(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getVerticesY();
    }

    int[] getVerticesZ(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getVerticesZ();
    }

    int[] getXTriangles(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getXTriangles();
    }

    int[] getYTriangles(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getYTriangles();
    }

    int[] getZTriangles(){// TODO: 6/12/2017 Can this be null?
        return rsModel.getZTriangles();
    }

    public RSModel getRsModel() {
        return rsModel;
    }
}
