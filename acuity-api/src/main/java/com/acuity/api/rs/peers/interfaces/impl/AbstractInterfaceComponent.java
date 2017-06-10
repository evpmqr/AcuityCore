package com.acuity.api.rs.peers.interfaces.impl;

import com.acuity.rs.api.RSInterfaceComponent;


public abstract class AbstractInterfaceComponent {

    private final RSInterfaceComponent component;

    public AbstractInterfaceComponent(RSInterfaceComponent component) {
        this.component = component;
    }

    public String[] getActions() {
        return component.getActions();
    }

    public int getBorderThickness() {
        return component.getBorderThickness();
    }

    public int getBoundsIndex() {
        return component.getBoundsIndex();
    }

    public int getComponentIndex() {
        return component.getComponentIndex();
    }

    public int getContentType() {
        return component.getType();
    }

    public int getFondId() {
        return component.getFondId();
    }

    public int getHeight() {
        return component.getHeight();
    }

    public int getInsetY() {
        return component.getInsetY();
    }

    public int getItemId() {
        return component.getItemId();
    }

    public int[] getItemIds() {
        return component.getItemIds();
    }

    public int getItemStackSize() {
        return component.getItemStackSize();
    }

    public int[] getItemStackSizes() {
        return component.getItemStackSizes();
    }

    public int getModelId() {
        return component.getModelId();
    }

    public int getModelType() {
        return component.getModelType();
    }

    public String getName() {
        return component.getName();
    }

    public String getNullSafeName() {
        return component.getName() == null ? "" : component.getName();
    }

    public int getOpacity() {
        return component.getOpacity();
    }

    public int getOriginalWidth() {
        return component.getOriginalWidth();
    }

    public int getOriginalX() {
        return component.getOriginalX();
    }

    public int getOriginalY() {
        return component.getOriginalY();
    }


    public int getParentUid() {
        return component.getParentUid();
    }

    public int getRelativeX() {
        return component.getRelativeX();
    }

    public int getRelativeY() {
        return component.getRelativeY();
    }

    public int getRotationX() {
        return component.getRotationX();
    }

    public int getRotationY() {
        return component.getRotationY();
    }

    public int getRotationZ() {
        return component.getRotationZ();
    }

    public int getScrollHeight() {
        return component.getScrollHeight();
    }

    public int getScrollWidth() {
        return component.getScrollWidth();
    }

    public int getScrollX() {
        return component.getScrollX();
    }

    public String getSelectedAction() {
        return component.getSelectedAction();
    }

    public int getShadowColor() {
        return component.getShadowColor();
    }

    public int getSpriteId() {
        return component.getSpriteId();
    }

    public String[] getTableActions() {
        return component.getTableActions();
    }

    public String getText() {
        return component.getText();
    }

    public String getNullSafeText() {
        return component.getText() == null ? "" : component.getText();
    }

    public int getTextColor() {
        return component.getTextColor();
    }

    public String getTooltip() {
        return component.getTooltip();
    }

    public int getType() {
        return component.getType();
    }

    public int getUid() {
        return component.getUid();
    }

    public int getWidth() {
        return component.getWidth();
    }

    public int getXPadding() {
        return component.getXPadding();
    }

    public int getYPadding() {
        return component.getYPadding();
    }

    public boolean isFlippedHorizontally() {
        return component.isFlippedHorizontally();
    }

    public boolean isFlippedVertically() {
        return component.isFlippedVertically();
    }

    public boolean isHidden() {
        return component.isHidden();
    }

    public boolean isVisible() {
        return !component.isHidden();
    }

    public boolean isScriptAvailable() {
        return component.isScriptAvailable();
    }


}
