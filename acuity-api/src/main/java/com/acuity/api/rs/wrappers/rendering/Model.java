package com.acuity.api.rs.wrappers.rendering;

import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.utils.Projection;
import com.acuity.rs.api.RSModel;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Zachary Herridge on 6/12/2017.
 */
public class Model extends Renderable {

    private RSModel rsModel;

    private int[] xVertices, yVertices, zVertices;
    private int[] xTriangles, yTriangles, zTriangles;

    @ClientInvoked
    public Model(RSModel peer) {
        super(peer);
        this.rsModel = Preconditions.checkNotNull(peer);


        int[] x = peer.getVerticesX();
        int[] y = peer.getVerticesY();
        int[] z = peer.getVerticesZ();

        int[] trianglesX = peer.getXTriangles();
        int[] trianglesY = peer.getYTriangles();
        int[] trianglesZ = peer.getZTriangles();
        if (x != null && y != null && z != null && trianglesX != null && trianglesY != null && trianglesZ != null){
            this.xTriangles = Arrays.copyOf(trianglesX, trianglesX.length);
            this.yTriangles = Arrays.copyOf(trianglesY, trianglesY.length);
            this.zTriangles = Arrays.copyOf(trianglesZ, trianglesZ.length);
            this.xVertices = Arrays.copyOf(x, x.length);
            this.yVertices = Arrays.copyOf(y, y.length);
            this.zVertices = Arrays.copyOf(z, z.length);
        }
    }

    public int[] getXVertices(){// TODO: 6/12/2017 Rename
        return xVertices;
    }

    public int[] getYVertices(){// TODO: 6/12/2017 Rename
        return yVertices;
    }

    public int[] getZVertices(){// TODO: 6/12/2017 Rename
        return zVertices;
    }

    public int[] getXTriangles(){
        return xTriangles;
    }

    public int[] getYTriangles(){
        return yTriangles;
    }

    public int[] getZTriangles(){
        return zTriangles;
    }

    public Stream<Point> getPoints(int strictX, int strictY) {
        final Stream.Builder<Point> points = Stream.builder();
        for (int i = 0; i < this.getXTriangles().length; i++) {
            final int x = this.getXTriangles()[i];
            final int y = this.getYTriangles()[i];
            final int z = this.getZTriangles()[i];

            final Point xx = Projection.worldToScreen((strictX - this.getXVertices()[x]), (strictY - this.getZVertices()[x]), -this.getYVertices()[x]).orElse(null);
            final Point yy = Projection.worldToScreen((strictX - this.getXVertices()[y]), (strictY - this.getZVertices()[y]), -this.getYVertices()[y]).orElse(null);
            final Point zz = Projection.worldToScreen((strictX - this.getXVertices()[z]), (strictY - this.getZVertices()[z]), -this.getYVertices()[z]).orElse(null);

            if (xx != null && yy != null && zz != null) {
                final int screenX = (xx.x + yy.x + zz.x) / 3;
                final int screenY = (xx.y + yy.y + zz.y) / 3;
                points.add(new Point(screenX, screenY));
            }
        }
        return points.build();
    }

    @NotNull
    public RSModel getRsModel() {
        return rsModel;
    }
}
