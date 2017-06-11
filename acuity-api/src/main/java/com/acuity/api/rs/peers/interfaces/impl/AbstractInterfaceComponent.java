package com.acuity.api.rs.peers.interfaces.impl;

import com.acuity.api.rs.peers.Node;
import com.acuity.rs.api.RSInterfaceComponent;
import com.google.common.base.Preconditions;


public abstract class AbstractInterfaceComponent extends Node {

    protected final RSInterfaceComponent rsInterfaceComponent;

    public AbstractInterfaceComponent(RSInterfaceComponent peer) {
        super(peer);
        this.rsInterfaceComponent = Preconditions.checkNotNull(peer);
    }

    public String[] getActions() {
        return rsInterfaceComponent.getActions();
    }

    public int getBorderThickness() {
        return rsInterfaceComponent.getBorderThickness();
    }

    public int getBoundsIndex() {
        return rsInterfaceComponent.getBoundsIndex();
    }

    public int getComponentIndex() {
        return rsInterfaceComponent.getComponentIndex();
    }

    public int getContentType() {
        return rsInterfaceComponent.getType();
    }

    public int getFondId() {
        return rsInterfaceComponent.getFondId();
    }

    public int getHeight() {
        return rsInterfaceComponent.getHeight();
    }

    public int getInsetY() {
        return rsInterfaceComponent.getInsetY();
    }

    public int getItemId() {
        return rsInterfaceComponent.getItemId();
    }

    public int[] getItemIds() {
        return rsInterfaceComponent.getItemIds();
    }

    public int getItemStackSize() {
        return rsInterfaceComponent.getItemStackSize();
    }

    public int[] getItemStackSizes() {
        return rsInterfaceComponent.getItemStackSizes();
    }

    public int getModelId() {
        return rsInterfaceComponent.getModelId();
    }

    public int getModelType() {
        return rsInterfaceComponent.getModelType();
    }

    public String getName() {
        return rsInterfaceComponent.getName();
    }

    public String getNullSafeName() {
        return rsInterfaceComponent.getName() == null ? "" : rsInterfaceComponent.getName();
    }

    public int getOpacity() {
        return rsInterfaceComponent.getOpacity();
    }

    public int getOriginalWidth() {
        return rsInterfaceComponent.getOriginalWidth();
    }

    public int getOriginalX() {
        return rsInterfaceComponent.getOriginalX();
    }

    public int getOriginalY() {
        return rsInterfaceComponent.getOriginalY();
    }

    public int getParentUid() {
        return rsInterfaceComponent.getParentUid();
    }

    public int getRelativeX() {
        return rsInterfaceComponent.getRelativeX();
    }

    public int getRelativeY() {
        return rsInterfaceComponent.getRelativeY();
    }

    public int getRotationX() {
        return rsInterfaceComponent.getRotationX();
    }

    public int getRotationY() {
        return rsInterfaceComponent.getRotationY();
    }

    public int getRotationZ() {
        return rsInterfaceComponent.getRotationZ();
    }

    public int getScrollHeight() {
        return rsInterfaceComponent.getScrollHeight();
    }

    public int getScrollWidth() {
        return rsInterfaceComponent.getScrollWidth();
    }

    public int getScrollX() {
        return rsInterfaceComponent.getScrollX();
    }

    public String getSelectedAction() {
        return rsInterfaceComponent.getSelectedAction();
    }

    public int getShadowColor() {
        return rsInterfaceComponent.getShadowColor();
    }

    public int getSpriteId() {
        return rsInterfaceComponent.getSpriteId();
    }

    public String[] getTableActions() {
        return rsInterfaceComponent.getTableActions();
    }

    public String getText() {
        return rsInterfaceComponent.getText();
    }

    public String getNullSafeText() {
        return rsInterfaceComponent.getText() == null ? "" : rsInterfaceComponent.getText();
    }

    public int getTextColor() {
        return rsInterfaceComponent.getTextColor();
    }

    public String getTooltip() {
        return rsInterfaceComponent.getTooltip();
    }

    public int getType() {
        return rsInterfaceComponent.getType();
    }

    public int getUid() {
        return rsInterfaceComponent.getUid();
    }

    public int getWidth() {
        return rsInterfaceComponent.getWidth();
    }

    public int getXPadding() {
        return rsInterfaceComponent.getXPadding();
    }

    public int getYPadding() {
        return rsInterfaceComponent.getYPadding();
    }

    public boolean isFlippedHorizontally() {
        return rsInterfaceComponent.isFlippedHorizontally();
    }

    public boolean isFlippedVertically() {
        return rsInterfaceComponent.isFlippedVertically();
    }

    public boolean isHidden() {
        return rsInterfaceComponent.isHidden();
    }

    public boolean isVisible() {
        return !rsInterfaceComponent.isHidden();
    }

    public boolean isScriptAvailable() {
        return rsInterfaceComponent.isScriptAvailable();
    }

    public RSInterfaceComponent getRsComponent() {
        return rsInterfaceComponent;
    }
}
