package com.acuity.api.rs.wrappers.scene;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.wrappers.rendering.scene.elements.SceneBoundaryDecor;
import com.acuity.api.rs.wrappers.scene.elements.ISceneElement;
import com.acuity.api.rs.wrappers.scene.elements.SceneBoundary;
import com.acuity.api.rs.wrappers.scene.elements.SceneElement;
import com.acuity.api.rs.wrappers.scene.elements.SceneTileDecor;
import com.acuity.api.rs.wrappers.structures.Node;
import com.acuity.rs.api.*;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/9/2017.
 */
public class SceneTile extends Node {

    private static final Logger logger = LoggerFactory.getLogger(SceneTile.class);

    private RSSceneTile rsSceneTile;

    @ClientInvoked
    public SceneTile(@NotNull RSSceneTile peer) {
        super(peer);
        this.rsSceneTile = Preconditions.checkNotNull(peer);
    }

    public Stream<ISceneElement> streamElements(){
        Stream.Builder<ISceneElement> builder = Stream.builder();
        for (RSSceneElement rsSceneElement : rsSceneTile.getElements()) {
            if (rsSceneElement != null) builder.accept(rsSceneElement.getWrapper());
        }
        getBoundary().ifPresent(builder::accept);
        getTileDecor().ifPresent(builder::accept);
        getBoundaryDecor().ifPresent(builder::accept);
        return builder.build();
    }

    public Optional<SceneBoundaryDecor> getBoundaryDecor(){
        return Optional.ofNullable(rsSceneTile.getBoundaryDecor()).map(RSSceneBoundaryDecor::getWrapper);
    }

    public Optional<SceneTileDecor> getTileDecor(){
        return Optional.ofNullable(rsSceneTile.getTileDecor()).map(RSSceneTileDecor::getWrapper);
    }

    public SceneElement[] getElements(){
        return Arrays.stream(rsSceneTile.getElements())// TODO: 6/12/2017 Can this be null?
                .map(rsSceneElement -> rsSceneElement != null ? rsSceneElement.getWrapper() : null)
                .toArray(SceneElement[]::new);
    }

    public Optional<SceneBoundary> getBoundary(){
        return Optional.ofNullable(rsSceneTile.getBoundary()).map(RSSceneBoundary::getWrapper);
    }

    @NotNull
    public RSSceneTile getRsSceneTile() {
        return rsSceneTile;
    }
}
