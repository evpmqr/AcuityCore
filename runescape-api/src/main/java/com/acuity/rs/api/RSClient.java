package com.acuity.rs.api;

//Generated

public interface RSClient extends RSGameEngine {

    int getOnCursorCount();

    int[] getMenuOpcodes();

    int[] getNpcIndices();

    int getHintArrowNpcIndex();

    int[] getInterfacePositionY();

    int[] getInterfacePositionX();

    long getLastClickTime();

    com.acuity.rs.api.RSCollisionData[] getCollisionMaps();

    java.lang.String getWorldDomain();

    com.acuity.rs.api.RSHashTable getItemContainers();

    com.acuity.rs.api.RSNodeDeque[][][] getGroundItemDeque();

    com.acuity.rs.api.RSNodeTable getNpcModelCache();

    java.lang.String[] getMenuActions();

    int[] getPlayerMenuTypes();

    int getBaseSceneY();

    int[] getInterfaceItemTriggers();

    int getSocketState();

    int getGameState();

    byte[][][] getSceneRenderRules();

    com.acuity.rs.api.RSFriendsListMember[] getFriends();

    int[] getVarps();

    int getMenuRowCount();

    int getLoginIndex();

    void setLoginIndex(int loginIndex);

    java.lang.String getLoginResponse1();

    void setLoginResponse1(java.lang.String loginResponse1);

    int getPendingClickX();

    int getHintArrowY();

    int getHintArrowX();

    java.lang.String getLoginResponse3();

    void setLoginResponse3(java.lang.String loginResponse3);

    java.lang.String getLoginResponse2();

    void setLoginResponse2(java.lang.String loginResponse2);

    int getPendingClickY();

    com.acuity.rs.api.RSSceneGraph getSceneGraph();

    java.lang.String[] getPlayerActions();

    int getCanvasWidth();

    int getMinimapOffset();

    com.acuity.rs.api.RSPlayer getLocalPlayer();

    int getAudioEffectCount();

    com.acuity.rs.api.RSInterfaceComponent[][] getInterfaces();

    int getPendingMouseX();

    int[] getInterfaceHeights();

    int[] getMenuTertiaryArgs();

    int getViewportWidth();

    com.acuity.rs.api.RSNodeTable getSpriteCache();

    com.acuity.rs.api.RSNPC[] getNpcs();

    java.lang.String getUsername();

    void setUsername(java.lang.String username);

    boolean isIsDynamicRegion();

    int getCanvasHeight();

    boolean isLowMemory();

    int getFriendCount();

    java.lang.String getLatestSelectedItemName();

    boolean[] isPlayerOptionsPriorities();

    boolean[][] isRenderArea();

    int getCurrentWorld();

    int getCameraX();

    int getCameraZ();

    int getCameraY();

    int getCurrentWorldMask();

    int getCameraYaw();

    com.acuity.rs.api.RSNodeTable getItemSpriteCache();

    int getMapRotation();

    int[] getOnSursorUids();

    int getHintArrowType();

    int getPlayerIndex();

    com.acuity.rs.api.RSWorld[] getWorlds();

    int getClanMateCount();

    int getSpellTargetFlags();

    int getLoginState();

    byte getClanChatRank();

    int getViewportScale();

    int getMenuY();

    int getMenuX();

    com.acuity.rs.api.RSIndexData getIndexTextures();

    int getMouseY();

    int getMouseX();

    int getPlane();

    int getMenuHeight();

    int getBaseScenceX();

    int getRunEnergy();

    int getMenuWidth();

    boolean isIsResized();

    com.acuity.rs.api.RSModIcon[] getModIcons();

    boolean[] isValidInterfaces();

    int getCursorState();

    int[] getBoostedSkillLevels();

    int[] getRealSkillLevels();

    int[] getTempVarps();

    boolean isMembersWorld();

    int[] getSkillExperiences();

    int getLatestSelectedItemIndex();

    int getDrawingAreaTop();

    int[] getInterfaceWidths();

    com.acuity.rs.api.RSPacketBuffer getPacket();

    com.acuity.rs.api.RSMouseRecorder getMouseRecorder();

    com.acuity.rs.api.RSNodeDeque getProjectilesDeque();

    com.acuity.rs.api.RSNodeDeque getPendingSpawns();

    com.acuity.rs.api.RSAudioEffect[] getAudioEffects();

    com.acuity.rs.api.RSNodeTable getItemModelCache();

    int getPacketId();

    com.acuity.rs.api.RSSocket getSocket();

    com.acuity.rs.api.RSPlayer[] getPlayers();

    int[][] getXteaKeys();

    int getSelectedRegionTileX();

    int getSelectedRegionTileY();

    int getDestinationX();

    int getDestinationY();

    int getViewportHeight();

    com.acuity.rs.api.RSHashTable getInterfaceFlags();

    int[] getDrawingAreaPixels();

    int getDrawingAreaBottom();

    int getCameraPitch();

    boolean isViewportWalking();

    java.lang.String getClanChatOwner();

    int[] getMenuSecondaryArgs();

    boolean isMenuOpen();

    int getDrawingAreaHeight();

    int getItemSelectionState();

    int[][][] getTileHeights();

    int getRights();

    int getEngineCycle();

    int getFps();

    int[] getMenuPrimaryArgs();

    int getDrawingAreaWidth();

    com.acuity.rs.api.RSChatMessages getChatMessages();

    int getPacketLength();

    int getWeight();

    com.acuity.rs.api.RSPacketBuffer getPacket2();

    int getIgnoreCount();

    com.acuity.rs.api.RSHashTable getInterfaceNodes();

    int getClickY();

    int getClickX();

    int getMouseIdleTime();

    java.lang.String[] getMenuTargets();

    java.lang.String getPassword();

    void setPassword(java.lang.String password);

    int getDrawingAreaRight();

    com.acuity.rs.api.RSFont getFont_p12full();

    com.acuity.rs.api.RSIgnoreListMember[] getIgnores();

    com.acuity.rs.api.RSClanMember[] getClanMates();

    java.lang.String getClanChatName();

    com.acuity.rs.api.RSGrandExchangeOffer[] getGrandExchangeOffers();

    int getMapScale();

    int getInterfaceRoot();

    RSItemComposite invokeGetItemDefinition(int var0);

    boolean invokeLoadWorlds();

    void invokeGroundItemSpawned(int var0, int var1);

    void invokeGameDraw(RSInterfaceComponent var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

    void invokeSetRasterBuffer(int var0, int var1, int var2);
}
