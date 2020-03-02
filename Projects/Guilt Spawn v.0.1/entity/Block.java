/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity;

import com.joka.guiltspawn.entity.player.Player;
import com.joka.guiltspawn.framework.Texture;
import com.joka.guiltspawn.enviroment.Event;
import com.joka.guiltspawn.sound.Sound;
import com.joka.guiltspawn.framework.Animation;
import com.joka.guiltspawn.framework.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author jonde
 */
public class Block extends GameObject {

    private Texture tex;
    private int type, duration;
    private Handler handler;
    private Player player;
    private Animation blockAnimation;
    private int size;
    private boolean open = false;
    private int moved = 0;
    private int fireChange = 3;
    private int fireGone = 0;
    private int weaponId;
    private BufferedImage fire;
    private int timer = 30;


    public Block(float x, float y, int type, ObjectId id, Handler handler) {
        super(x, y, id);
        this.type = type;
        this.handler = handler;
        tex = handler.getGame().getTexture();
        if (type == 6 || type == 7) {
            blockAnimation = new Animation(5, tex.getBlock()[6], tex.getBlock()[7], tex.getBlock()[8], tex.getBlock()[9], tex.getBlock()[10], tex.getBlock()[11], tex.getBlock()[12], tex.getBlock()[11], tex.getBlock()[10], tex.getBlock()[9], tex.getBlock()[8], tex.getBlock()[7]);
            player = handler.getGame().getPlayer();
        }
        if (type == 7) {
            duration = 300;
        }
        if (type == 8) {
            blockAnimation = new Animation(20, tex.getObjects()[17], tex.getObjects()[18], tex.getObjects()[19], tex.getObjects()[20], tex.getObjects()[19], tex.getObjects()[18], tex.getObjects()[17]);
            this.size = handler.getGame().getRandom().nextInt(30);
            setVelX(handler.getGame().getRandom().nextInt(20) * -1);
            setVelY(handler.getGame().getRandom().nextInt(3) + 1);
        }
        if (type == 10) {
            this.setMaxHealth(30);
            this.setHealth(30);
        }
        if (type == 11) {
            fire = tex.getObjects()[23];
            this.size = handler.getGame().getRandom().nextInt(15);
            setVelX(handler.getGame().getRandom().nextInt(10) * -1);
            setVelY((handler.getGame().getRandom().nextInt(15) + 1) * -1);
        }
        if (type == 12){
            this.size = handler.getGame().getRandom().nextInt(100);
            setVelY((handler.getGame().getRandom().nextInt(7) + 1) * -1);
        }
    }

    public void collide() {
        if (getType() == 6 || type == 7) {
            if (getBounds().intersects(player.getBounds())) {
                player.addKarma(5);
                handler.removeObject(this);
                player.setHealth(player.getHealth() + 1);
                new Sound().play("snd/karma.WAV");
            } else if (getBounds().intersects(player.getBoundsTop())) {
                player.addKarma(5);
                handler.removeObject(this);
                player.setHealth(player.getHealth() + 1);
                new Sound().play("snd/karma.WAV");
            }
        }

    }

    public void render(Graphics g) {

        if (type == 6) // karma
        {
            blockAnimation.drawAnimation(g, (int) x, (int) y);
        }
        if (type == 7) // karma
        {
            double s = ((1.0 * duration) / (1.0 * 300));
            blockAnimation.drawAnimation(g, (int) x, (int) y, (int) (32 * s), (int) (32 * s));
        }
        if (type == 8) // flake
        {
            blockAnimation.drawAnimation(g, (int) x, (int) y, size, size);
        }
        if (type == 9) {
            g.drawImage(handler.getGame().getTexture2().getBlock()[14], (int) this.x, (int) this.y, null);
        }
        if (type == 10) {
            if (this.getHealth() > 20) {
                g.drawImage(handler.getGame().getTexture2().getBlock()[15], (int) this.x, (int) this.y, null);
            } else if (this.getHealth() > 10) {
                g.drawImage(handler.getGame().getTexture2().getBlock()[16], (int) this.x, (int) this.y, null);
            } else if (this.getHealth() > 0) {
                g.drawImage(handler.getGame().getTexture2().getBlock()[17], (int) this.x, (int) this.y, null);
            }
        }
        if (type == 11) {
            Random random = handler.getGame().getRandom();
            if (fireGone >= fireChange) {
                int i = random.nextInt(8);
                switch (i) {
                    case 0:
                        fire = tex.getObjects()[23];
                        break;
                    case 1:
                        fire = tex.getObjects()[24];
                        break;
                    case 2:
                        fire = tex.getObjects()[25];
                        break;
                    case 3:
                        fire = tex.getObjects()[26];
                        break;
                    case 4:
                        fire = tex.getObjects()[27];
                        break;
                    case 5:
                        fire = tex.getObjects()[28];
                        break;
                    case 6:
                        fire = tex.getObjects()[29];
                        break;
                    case 7:
                        fire = tex.getObjects()[30];
                        break;
                }
                fireGone = 0;
            } else {
                fireGone++;
            }
            g.drawImage(fire, (int) x, (int) y, size, size, null);
        }

        if (type == 12) {
            g.drawImage(tex.getObjects()[31], (int) x, (int) y, size, size, null);
        }
    }

    public Rectangle getBounds() {
        if (type == 9) {
            return new Rectangle((int) x, (int) y, 32, 128);
        } else if (type == 10) {
            return new Rectangle((int) x, (int) y, 32, 192);
        } else {
            return new Rectangle((int) x, (int) y, 32, 32);
        }

    }

    @Override
    public int getType() {
        return this.type;
    }
    
    public void setOpen(boolean open){
        this.open = open;
    }

    public void checkDuration() {
        if (duration > 0) {
            duration--;
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void tick() {
        if (getType() == 6 || getType() == 7 || getType() == 8) {
            collide();
            slowDown();
            blockAnimation.runAnimation();
            if (getType() == 7) {
                x += velX;
                if (duration % 3.4 == 0) {
                    x += (1 * facing);
                }
                checkDuration();
            } else if (getType() == 6) {
                x += velX;
            }
            if (getType() == 8) {
                x -= 1;
                if(this.getY() > 1080){
                    handler.removeObject(this);
                }
            }
            y += velY;
        } else if (getType() == 9) {
            if(this.open == true){
                if(this.moved < 192){
                    this.y++;
                    this.moved++;
                } else {
                    handler.removeObject(this);
                }
            }
        } else if (getType() == 10) {
            if (this.getHealth() <= 0) {
                handler.removeObject(this);
                if(handler.getGate() != null){
                    handler.getGate().setOpen(true);
                    handler.setEvent(new Event(handler));
                }
            }
            this.spendInvulnerability();
        } else if (getType() == 11) {
            x -= 1;
            y += velY;
            timer--;
            if(timer <= 0){
                size--;
                timer = 30;
            }
            if(this.getY() < -48 || this.size <= 0){
                handler.removeObject(this);
            }
        } else if (getType() == 12) {
            y += velY;
            timer--;

                size++;

            if(this.getY() < -500){
                handler.removeObject(this);
            }
        }

    }

}
