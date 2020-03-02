/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiltspawn.entity;

import guiltspawn.engine.TileMap;
import guiltspawn.graphics.Animation;
import guiltspawn.graphics.Sprite;
import guiltspawn.sound.SoundEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author jonde
 */
public class Weapon extends MapObject {

    private Sprite s;
    private boolean hit, remove;
    private BufferedImage[] sprites;
    private Animation ani;

    public Weapon(TileMap tm) {
        super(tm);
        width = 40;
        height = 40;
        cwidth = 80;
        cheight = 40;
        this.hit = false;
        this.remove = false;
        facingRight = tileMap.getGame().getHandler().getPlayer().getFacingRight();
        setX(tileMap.getGame().getHandler().getPlayer().getX());
        setY(tileMap.getGame().getHandler().getPlayer().getY());
        s = new Sprite("/sprites/weapon/w1.png");
        // load sprites here
        loadSprites();

    }

    public void loadSprites() {
        sprites = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            sprites[i] = s.sub(0, i, width, height);
        }
        ani = new Animation();
        ani.setAnimation(sprites);
        ani.setSpeed(4);
    }

    @Override
    public boolean intersects(MapObject o) {
        return this.getRectangle().intersects(((Enemy) o).getRectangle());
    }

    @Override
    public Rectangle getRectangle() {
        if (facingRight) {
            return new Rectangle((int) getX() + 35, (int) getY() - (height / 2), cwidth, cheight);
        } else {
            return new Rectangle((int) getX() - 115, (int) getY() - (height / 2), cwidth, cheight);
        }
    }

    @Override
    public void tick() {
        x += tileMap.getGame().getHandler().getPlayer().getDx();
        y += tileMap.getGame().getHandler().getPlayer().getDy();
        setPosition(x, y);
        ani.tick();
        if (!this.hit) {
            //check enemy collisions
            ArrayList<Enemy> enemies = tileMap.getGame().getHandler().getEnemies();
            boolean flag = false;
            for (Enemy e : enemies) {
                if (this.getRectangle().intersects(e.getRectangle())) {
                    e.hit(tileMap.getGame().getHandler().getPlayer().getAttackDamage());
                    SoundEffect.HIT.play();
                    flag = true;
                }
            }
            if (flag) {
                this.hit = true;
            }
        }
        if (ani.hasPlayedOnce()) {
            remove = true;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setMapPosition();
        if (facingRight) {
            //g2.draw(getRectangle());
            //g.drawRect((int) getX(), (int) getY() - (height / 2), cwidth, cheight);
            g.drawImage(this.ani.getImage(), (int) getX(), (int) getY() - (height / 2), cwidth, cheight, null);
        } else {
            //g2.draw(getRectangle());
            //g.drawRect((int) getX() - cwidth, (int) getY() - (height / 2), cwidth, cheight);
            g.drawImage(this.ani.getImage(), (int) getX(), (int) getY() - (height / 2), -cwidth, cheight, null);
        }
        g.setColor(Color.BLUE);
        //g.drawRect(getX(), getY(), 2, 2);
    }

    ///////////////////////////////GETTERS AND SETTERS//////////////////////////
    public void setHit(boolean hit) {
        if (hit) {
            return;
        }
        this.hit = hit;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public Animation getAnimation() {
        return this.ani;
    }

}
