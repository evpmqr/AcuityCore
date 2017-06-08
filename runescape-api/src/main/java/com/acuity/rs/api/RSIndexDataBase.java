package com.acuity.rs.api;

//Generated

public interface RSIndexDataBase {

    int[] getArchiveNames();

    int[] getArchiveCrcs();

    int[][] getArchiveFileIds();

    com.acuity.rs.api.RSIdentityTable[] getChildren();

    int[] getArchiveRevisions();

    com.acuity.rs.api.RSIdentityTable getEntry();

    int[] getArchiveIds();

    java.lang.Object[][] getBuffer();

    int getValidArchivesCount();

    int[][] getArchiveFileNames();

    int[] getArchiveNumberOfFiles();

    byte invokeUnpack(int var0, int var1, int var2);
}
