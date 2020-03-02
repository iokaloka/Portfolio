/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.enviroment;

import com.joka.guiltspawn.entity.player.Player;
import com.joka.guiltspawn.entity.GameObject;
import com.joka.guiltspawn.entity.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author jonde
 */
public class MoveZone extends GameObject {

    private Player player;

    public MoveZone(float x, float y, ObjectId id, Player player) {
        super(x, y, id);
        this.player = player;
    }

    public void tick() {
        int a = (int) x;
        int b = (int) player.getX();
        int c = (int) y;
        int d = (int) player.getY();
        if (player.getX() <= x) {
            x -= Math.abs(Math.abs(b) - Math.abs(a));
        } else if (player.getX() >= x + 252) {
            x += Math.abs((Math.abs(b) - Math.abs(a)) - 252);
        }
        if (player.getY() <= y) {
            y -= Math.abs(Math.abs(d) - Math.abs(c));
        } else if (player.getY() >= y + 204) {
            y += Math.abs((Math.abs(d) - Math.abs(c)) - 204);
        }
        
        if (this.x < player.getHandler().getLs() + 700) {
            this.x = player.getHandler().getLs() + 700;
        } else if (this.x > player.getHandler().getLe() -960) {
            this.x = player.getHandler().getLe() - 960;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, 300, 300);
    }


}
