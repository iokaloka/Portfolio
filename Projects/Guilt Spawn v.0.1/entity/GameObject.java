/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity;

import com.joka.guiltspawn.enviroment.MoveZone;
import com.joka.guiltspawn.main.Game;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author jonde
 */
public abstract class GameObject {

    protected float x, y, velX = 0, velY = 0, speedModifier = 0, speedModifierV = 0;
    protected int health = 100, maxHealth = 0, facing = 1, facingV = 1, karma = 0, invulnerable = 0, defence = 1, strength = 1;
    protected ObjectId id;
    protected boolean falling = true, jumping = false, attacking = false, attackingR = false, attackingL = false, alive = true, activated = false;
    ;
    private MoveZone zone;
    private int currentLevel;
    private int destination;
    private Action action;
    private boolean active = false;

    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /////////////////////
    /////////////////////ABSTRACT METHODS///////////////////////////////////////
    /////////////////////
    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    ////////////////////
    ////////////////////BASIC METHODS///////////////////////////////////////////
    ////////////////////
    public void act() {
        this.getAction().act();
        this.activated = true;
    }

    public void addKarma(int karma) {
        this.karma += karma;
    }

    public boolean checkInvulnerably() {
        if (this.getInvulnerable() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void spendInvulnerability() {
        if (this.getInvulnerable() > 0) {
            this.setInvulnerable(this.getInvulnerable() - 1);
        }
    }

    public void knockback() {
        if (speedModifier == 0) {
            speedModifier = (10 * getFacing() * -1);
        }
    }

    public void knockbackV() {
        if (speedModifierV == 0) {
            speedModifierV = (10 * getFacingV() * -1);
        }
    }

    public void knockback(GameObject go) {
        if (this.facing != go.getFacing()) {
            if (this.getSpeedModifier() == 0) {
                if (go.getFacing() == -1) {
                    this.setSpeedModifier(-10);
                } else {
                    this.setSpeedModifier(10);
                }
            }
        } else {
            if (this.x + 24 <= go.getX() + 20) {
                this.setSpeedModifier(-10);
            } else {
                this.setSpeedModifier(10);
            }
        }
    }

    public void slowDown() {
        if (this.velX < 0) {
            this.velX++;
        } else if (this.velX > 0) {
            this.velX--;
        }
        if (this.velY < 0) {
            this.velY++;
        }
    }

    public void decSpeedModifiers() {
        if (getSpeedModifier() != 0) {
            if (getSpeedModifier() > 0) {
                setSpeedModifier(getSpeedModifier() - 1);
            } else {
                setSpeedModifier(getSpeedModifier() + 1);
            }
        }
        if (getSpeedModifierV() != 0) {
            if (getSpeedModifierV() > 0) {
                setSpeedModifierV(getSpeedModifierV() - 1);
            } else {
                setSpeedModifierV(getSpeedModifierV() + 1);
            }
        }
    }

    ////////////////////
    ////////////////////METHODS JUST TO BE OVERRIDDEN///////////////////////////
    ////////////////////
    public void attack() {
    }

    public void attackR() {
    }

    public void attackL() {
    }

    public int getType() {
        return 0;
    }

    public void setType() {}

    public void resetAnimationIn() {
    }

    public void resetAnimationOut() {
    }

    public int getDoorNumber(){
        return 0;
    }
    ///////////////////
    ///////////////////////////GETTERS AND SETTERS//////////////////////////////
    ///////////////////
    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return this.active;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean getAttacking() {
        return this.attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean getAttackingL() {
        return this.attackingL;
    }

    public void setAttackingL(boolean value) {
        this.attackingL = value;
    }

    public boolean getAttackingR() {
        return this.attackingR;
    }

    public void setAttackingR(boolean value) {
        this.attackingR = value;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getDef() {
        return this.defence;
    }

    public void setDef(int def) {
        this.defence = def;
    }

    public int getDestination() {
        return this.destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getFacingV() {
        return facingV;
    }

    public void setFacingV(int facingV) {
        this.facingV = facingV;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setGameLevel() {
        Game.Companion.setLEVEL(this.getDestination());
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0;
        }
        if (this.getHealth() >= this.getMaxHealth()) {
            this.health = this.getMaxHealth();
        }
    }

    public ObjectId getId() {
        return id;
    }

    public int getInvulnerable() {
        return this.invulnerable;
    }

    public void setInvulnerable(int inv) {
        this.invulnerable = inv;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getKarma() {
        return this.karma;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
        if (this.maxHealth < 0) {
            this.maxHealth = 0;
        }
    }

    public MoveZone getMoveZone() {
        return this.zone;
    }

    public void setMoveZone(MoveZone zonee) {
        this.zone = zonee;
    }

    public float getSpeedModifier() {
        return this.speedModifier;
    }

    public void setSpeedModifier(float modifier) {
        this.speedModifier = modifier;
    }

    public float getSpeedModifierV() {
        return this.speedModifierV;
    }

    public void setSpeedModifierV(float modifierV) {
        this.speedModifierV = modifierV;
    }

    public int getStr() {
        return this.strength;
    }

    public void setStr(int str) {
        this.strength = str;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
