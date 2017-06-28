package com.acuity.rs.api;


//Generated by the injector on run.

public interface RSKitDefinition extends RSCacheableNode {

	int getBodyPartId();

	int[] getModelIds();

	int[] getModels();

	short[] getRecolorToFind();

	short[] getRecolorToReplace();

	short[] getRetextureToFind();

	short[] getRetextureToReplace();

	com.acuity.rs.api.RSModelComposite invokeGetModelData(byte var0);

	boolean invokeReady(int var0);

	boolean isNonSelectable();

	void setBodyPartId(int var0);

	void setModelIds(int[] var0);

	void setModels(int[] var0);

	void setNonSelectable(boolean var0);

	void setRecolorToFind(short[] var0);

	void setRecolorToReplace(short[] var0);

	void setRetextureToFind(short[] var0);

	void setRetextureToReplace(short[] var0);
}
