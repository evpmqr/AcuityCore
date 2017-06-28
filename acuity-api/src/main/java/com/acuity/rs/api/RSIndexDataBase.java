package com.acuity.rs.api;


//Generated by the injector on run.

public interface RSIndexDataBase {

	int[] getArchiveCrcs();

	int[][] getArchiveFileIds();

	int[][] getArchiveFileNames();

	int[] getArchiveIds();

	int[] getArchiveNames();

	int[] getArchiveNumberOfFiles();

	int[] getArchiveRevisions();

	java.lang.Object[][] getBuffer();

	com.acuity.rs.api.RSIdentityTable[] getChildren();

	com.acuity.rs.api.RSIdentityTable getEntry();

	int getValidArchivesCount();

	byte[] invokeGetConfigData(int var0, int var1, int var2);

	byte[] invokeUnpack(int var0, int var1, int[] var2, int var3);

	void setArchiveCrcs(int[] var0);

	void setArchiveFileIds(int[][] var0);

	void setArchiveFileNames(int[][] var0);

	void setArchiveIds(int[] var0);

	void setArchiveNames(int[] var0);

	void setArchiveNumberOfFiles(int[] var0);

	void setArchiveRevisions(int[] var0);

	void setBuffer(java.lang.Object[][] var0);

	void setChildren(com.acuity.rs.api.RSIdentityTable[] var0);

	void setEntry(com.acuity.rs.api.RSIdentityTable var0);

	void setValidArchivesCount(int var0);
}
