package com.acuity.rs.api;

//Generated

public interface RSModelComposite extends RSRenderable {

    byte[] getTextureRenderTypes();

    int getTriangleFaceCount();

    int[] getVertexX();

    byte[] getFaceRenderPriorities();

    byte[] getTextureCoords();

    int[] getVertexZ();

    int[] getVertexY();

    byte getPriority();

    short[] getFaceTextures();

    short[] getFaceColor();

    byte[] getFaceRenderType();

    int getVertexCount();

    int[] getTriangleSkinValues();

    com.acuity.rs.api.RSNormalVertex[] getNormals();

    short[] getTexTriangleZ();

    short[] getTexTriangleX();

    short[] getTexTriangleY();

    short[] getTexturePrimaryColor();

    byte[] getFaceAlphas();

    int[] getTrianglePointsX();

    int[] getTrianglePointsY();

    int[] getVertexSkins();

    com.acuity.rs.api.RSNormalFace[] getFaceNormals();

    int[] getTrianglePointsZ();

    RSModel invokeLight(int var0, int var1, int var2, int var3, int var4);

    void invokeComputeAnimationTables();

    void invokeComputeNormals();
}
