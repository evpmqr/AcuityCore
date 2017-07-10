package com.acuity.api.meta;

/**
 * Created by Zachary Herridge on 7/10/2017.
 */
public class AcuitySettings {

    private boolean modelCachingEnabled = true;


    public boolean isModelCachingEnabled() {
        return modelCachingEnabled;
    }

    public void setModelCachingEnabled(boolean modelCachingEnabled) {
        this.modelCachingEnabled = modelCachingEnabled;
    }
}


