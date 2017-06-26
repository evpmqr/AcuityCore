package com.acuity.rs.api;

import com.acuity.api.rs.wrappers.rendering.Model;
import com.acuity.api.rs.wrappers.rendering.Renderable;

//Generated by the injector on run.

//Extends: RSCacheableNode
public interface RSRenderable extends RSCacheableNode {

    Model getCachedModel();

    int getHeight();

    Renderable getWrapper();

    com.acuity.rs.api.RSModel invokeGetModel();
}
