/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.framework;

import com.joka.guiltspawn.entity.player.Player;
import com.joka.guiltspawn.sound.Sound;
import com.joka.guiltspawn.main.Game;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author jonde
 */
public class MouseInput implements MouseListener {

    private Handler handler;

    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        chooseEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        chooseEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    public void chooseEvent(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        if (Game.Companion.getState() == Game.STATE.MENU) {

            if (x >= 450 * 2.4 && x <= 580 * 2.4 && y >= 240 * 1.8 && y <= 280 * 1.8) {
                handler.loadStartLevel();
                Game.Companion.setState(Game.STATE.GAME);
                new Sound().play("snd/skrii.WAV");
            } else if (x >= 450 * 2.4 && x <= 580 * 2.4 && y >= 280 * 1.8 && y <= 320 * 1.8) {
                System.out.println("Continue");
            } else if (x >= 450 * 2.4 && x <= 580 * 2.4 && y >= 320 * 1.8 && y <= 360 * 1.8) {
                System.out.println("Options");
            } else if (x >= 450 * 2.4 && x <= 580 * 2.4 && y >= 360 * 1.8 && y <= 400 * 1.8) {
                System.exit(0);
            }

        } else if (Game.Companion.getState() == Game.STATE.GAME) {
            if (handler.getGame().getPlayer().getWeaponR() != null) {
                handler.getGame().getPlayer().getWeaponR().setFacing(handler.getGame().getPlayer().getFacing());
                handler.getGame().getPlayer().attackR();
            }
        } else if (Game.Companion.getState() == Game.Companion.getState().PAUSE) {

            if (x >= 348 && x <= 458 && y >= 212 && y <= 242) {
                System.out.println("Continue");
            } else if (x >= 348 && x <= 458 && y >= 251 && y <= 281) {
                System.out.println("Options");
            } else if (x >= 348 && x <= 458 && y >= 290 && y <= 320) {
                System.out.println("Save");
            } else if (x >= 348 * 2.4 && x <= 458 * 2.4 && y >= 329 * 1.8 && y <= 359 * 1.8) {
                System.exit(0);
            }
        } else if (Game.Companion.getState() == Game.Companion.getState().FAIL) {
            //g.drawRect(480, 285, 130, 50);
            if (x >= 480 * 2.4 && x <= 610 * 2.4 && y >= 285 * 1.8 && y <= 335 * 1.8) {
                Player player = handler.getGame().getPlayer();
                player.reset();
                handler.restart();

                handler.getGame().setFailTrue(false);
                Game.Companion.setState(Game.Companion.getState().GAME);
                new Sound().play("snd/skrii.WAV");
            }
        }
    }

    public int getX() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        return point.x;
    }

    public int getY() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        return point.y;
    }

}
