package com.acuity.api.rs.utils;

public class UIDs {

    public static int compile(int sceneX, int sceneY, int id, int type, boolean interactable) {
        sceneX &= 127;    // Maximum value of 127
        sceneY &= 127;    // Maximum value of 127
        id &= 32767;      // Maximum value of 32767
        type &= 3;        // Maximum value of 3
        int uid = (type << 29) + (id << 14) + (sceneY << 7) + (sceneX); //parenthesis for clarity
        if (!interactable) {
            uid -= Integer.MIN_VALUE; //Set the sign bit to 1
        }
        return uid;
    }

    public static int getSceneX(int uid) {
        return uid & 0x7f;
    }

    public static int getSceneY(int uid) {
        return uid >> 7 & 0x7f;
    }

    public static int getEntityID(int uid) {
        return uid >> 14 & 0x7fff;
    }

    public static Type getEntityType(int uid) {
        return Type.values()[uid >> 29 & 0x3];
    }

    //Checks the sign bit, checking if it's positive or negative is a faster/clever alternative
    public static boolean isInteractable(int uid) {
        return uid > 0;
    }

    public enum Type {
        PLAYER,
        NPC,
        SCENE_ELEMENT,
        PICKABLE_DECOR
    }

    public static class UID {

        private final int uid;

        public UID(int uid) {
            this.uid = uid;
        }

        public int getUID() {
            return uid;
        }

        public int getSceneX() {
            return UIDs.getSceneX(uid);
        }

        public int getSceneY() {
            return UIDs.getSceneY(uid);
        }

        public int getEntityID() {
            return UIDs.getEntityID(uid);
        }

        public Type getEntityType() {
            return UIDs.getEntityType(uid);
        }

        public boolean isInteractable() {
            return UIDs.isInteractable(uid);
        }
    }
}
