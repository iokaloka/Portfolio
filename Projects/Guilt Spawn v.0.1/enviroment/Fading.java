/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.enviroment;

import com.joka.guiltspawn.entity.GameObject;
import com.joka.guiltspawn.entity.ObjectId;
import com.joka.guiltspawn.framework.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author jonde
 */
public class Fading extends GameObject {

    private BufferedImage img;
    private Handler handler;

    public Fading(float x, float y, ObjectId id, BufferedImage img, int direction, Handler handler) {
        super(x, y, id);
        this.img = img;
        this.setFacing(direction);
        this.handler = handler;
    }

    public void tick() {
        this.x += 25 * this.getFacing();
        if (this.getX() <= -3840 || this.getX() >= 3840) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int) this.getX(), 0, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, 0, 0, 0);
    }

}
