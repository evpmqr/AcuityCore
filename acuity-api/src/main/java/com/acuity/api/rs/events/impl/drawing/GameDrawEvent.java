package com.acuity.api.rs.events.impl.drawing;

import com.acuity.api.rs.events.RSEvent;

import java.awt.*;

/**
 * Created by Zachary Herridge on 7/6/2017.
 */
public class GameDrawEvent implements RSEvent{

    private Image image;

    public GameDrawEvent(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Graphics getGraphics(){
        return image.getGraphics();
    }
}
