/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joka.guiltspawn.entity.player;

import com.joka.guiltspawn.Interfaces.IEnemy;
import com.joka.guiltspawn.Interfaces.ITrigger;
import com.joka.guiltspawn.framework.Animation;
import com.joka.guiltspawn.framework.Camera;
import com.joka.guiltspawn.framework.Handler;
import com.joka.guiltspawn.framework.Texture;
import com.joka.guiltspawn.entity.GameObject;
import com.joka.guiltspawn.entity.ObjectId;
import com.joka.guiltspawn.entity.Weapon;
import com.joka.guiltspawn.Interfaces.IBlock;
import com.joka.guiltspawn.main.Game;
import com.joka.guiltspawn.sound.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author jonde
 */
public class Player extends GameObject {

    private float width = 48, height = 96, gravity = 0.5f, screenX = 984, screenY = 584;
    private final float MAX_SPEED = 10;
    private int attackDuration = 0, jumpSpeed = 0, jumpSpeedMax = -22, lostHealth = 0;
    public int drive;
    boolean jumpCharge = false;
    private Animation currentAnimation = null;
    private Camera cam;
    private Handler handler;
    public Texture tex;
    private Animation playerWalkR;
    private Animation playerWalkL;
    private Animation playerWalkRTR;
    private Animation playerWalkLTR;
    private Animation playerWalkRTL;
    private Animation playerWalkLTL;
    private Animation jumpR;
    private Animation jumpL;
    private Weapon weaponR = null;
    private Weapon weaponL = null;

    public Player(float x, float y, Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        tex = handler.getGame().getTexture();
        this.cam = cam;
        playerWalkR = new Animation(5, tex.getMc()[0], tex.getMc()[1], tex.getMc()[0], tex.getMc()[2], tex.getMc()[3], tex.getMc()[2]);
        playerWalkL = new Animation(5, tex.getMc()[4], tex.getMc()[5], tex.getMc()[4], tex.getMc()[6], tex.getMc()[7], tex.getMc()[6]);
        playerWalkRTR = new Animation(5, tex.getMc()[24], tex.getMc()[25], tex.getMc()[24], tex.getMc()[26], tex.getMc()[27], tex.getMc()[26]);
        playerWalkLTR = new Animation(5, tex.getMc()[28], tex.getMc()[29], tex.getMc()[28], tex.getMc()[30], tex.getMc()[31], tex.getMc()[30]);
        playerWalkRTL = new Animation(5, tex.getMc()[32], tex.getMc()[33], tex.getMc()[32], tex.getMc()[34], tex.getMc()[35], tex.getMc()[34]);
        playerWalkLTL = new Animation(5, tex.getMc()[36], tex.getMc()[37], tex.getMc()[36], tex.getMc()[38], tex.getMc()[39], tex.getMc()[38]);
        jumpR = new Animation(5, tex.getMc()[8], tex.getMc()[9]);
        jumpL = new Animation(5, tex.getMc()[10], tex.getMc()[11]);
        currentAnimation = playerWalkR;

        setMaxHealth(100);
        setHealth(getMaxHealth());
    }

    @Override
    public void attackR() {
        if (weaponR != null) {
            if (!this.attacking) {
                setAttackingR(true);
            }
        }
    }

    @Override
    public void attackL() {
        if (weaponL != null) {
            if (!this.attacking) {
                setAttackingL(true);
            }
        }
    }

    public void jump() {
        jumpCharge = true;
        setJumping(true);
        currentAnimation = null;
    }

    public void spendDrive(){
        if(drive >= 3){
            drive -= 3;
            health += 2;
        }
    }

    public void reset() {
        setHealth(getMaxHealth());
    }

    public void tick() {

        if (!handler.getGame().getLoading()) {
            ///////////////////////MOVEZONE///////////////
            if (this.getMoveZone() == null) {
                setMoveZone(handler.getMoveZone());
            }
            if (getSpeedModifier() < 0) {
                setSpeedModifier(getSpeedModifier() + 1);
            } else if (getSpeedModifier() > 0) {
                setSpeedModifier(getSpeedModifier() - 1);
            }

            //////////////////////////JUMP////////////////////////
            if (jumpCharge == true) {
                if (jumpSpeed == -21) {
                    jumpSpeed += -1;
                } else if (jumpSpeed == -20) {
                    jumpSpeed += -2;
                } else {
                    jumpSpeed += -3;
                }
                if (jumpSpeed <= jumpSpeedMax) {
                    jumpCharge = false;
                }
            } else {
                if (jumpSpeed != 0) {
                    jumpSpeed++;
                }
            }

            /////////////////////////CENTERX////////////////
            x += (velX + getSpeedModifier());
            if (screenX >= 834 && screenX <= 1084) {
                screenX += (velX + getSpeedModifier());
            } else if (screenX <= 834) {
                screenX = 834;
            } else {
                screenX = 1084;
            }

            //////////////////////////////////////CENTERY///////////////////////
            y += velY + getSpeedModifierV() + jumpSpeed;
            if (screenY <= 710 && screenY >= 410) {
                screenY += (velY + getSpeedModifier());
            } else if (screenY <= 410) {
                screenY = 410;
            } else {
                screenY = 710;
            }

            ///////////////////////////////FALLING/////////////////////////////
            if (falling || jumping) {
                velY += gravity;
                if (velY > MAX_SPEED) {
                    velY = MAX_SPEED;
                }
            }

            //////////////////////COLLISION/////////////////////
            collision();
            //////////////////////TRANSFUSION///////////////////
            if(this.lostHealth > 3){
                this.lostHealth -= 3;
                this.drive += 3;
            }
            /////////////////////INVULNERABILITY////////////////////
            this.spendInvulnerability();
            /////////////////////ATTACKING//////////////////////
            if (this.attackDuration == 0) {
                setAttacking(false);
            }
            if (this.attackDuration > 0) {
                attackDuration--;
            }

            /////////////////ANIMATING/////////////////
            if (facing == 1 && velX != 0) {
                if (getWeaponR() == null && getWeaponL() == null) {
                    currentAnimation = playerWalkR;
                    playerWalkR.runAnimation();
                } else if (getWeaponR() != null && getWeaponL() == null) {
                    if (getWeaponR().getWeaponId() == 2) { //torch in right hand
                        currentAnimation = playerWalkRTR;
                        playerWalkRTR.runAnimation();
                    } else {
                        currentAnimation = playerWalkR;
                        playerWalkR.runAnimation();
                    }
                } else if (getWeaponR() == null && getWeaponL() != null) {
                    if (getWeaponL().getWeaponId() == 2) { // torch in left hand
                        currentAnimation = playerWalkRTL;
                        playerWalkRTL.runAnimation();
                    } else {
                        currentAnimation = playerWalkR;
                        playerWalkR.runAnimation();
                    }
                } else if (getWeaponR() != null && getWeaponL() != null) {
                    //kek
                }
            } else if (facing == -1 && velX != 0) {
                if (getWeaponR() == null && getWeaponL() == null) {
                    currentAnimation = playerWalkL;
                    playerWalkL.runAnimation();
                } else if (getWeaponR() != null && getWeaponL() == null) {
                    if (getWeaponR().getWeaponId() == 2) { //torch in right hand
                        currentAnimation = playerWalkLTR;
                        playerWalkLTR.runAnimation();
                    } else {
                        currentAnimation = playerWalkL;
                        playerWalkL.runAnimation();
                    }
                } else if (getWeaponR() == null && getWeaponL() != null) {
                    if (getWeaponL().getWeaponId() == 2) { // torch in left hand
                        currentAnimation = playerWalkLTL;
                        playerWalkLTL.runAnimation();
                    } else {
                        currentAnimation = playerWalkL;
                        playerWalkL.runAnimation();
                    }
                } else if (getWeaponR() != null && getWeaponL() != null) {
                    //kek
                }
            }

            if (jumping) {
                if (facing == 1) {
                    currentAnimation = jumpR;
                    jumpR.runAnimationOnce();
                } else {
                    currentAnimation = jumpL;
                    jumpL.runAnimationOnce();
                }
            } else {
                jumpR = new Animation(5, tex.getMc()[8], tex.getMc()[9]);
                jumpL = new Animation(5, tex.getMc()[10], tex.getMc()[11]);
            }

            /////////////////////////DEATH//////////////////////
            if (getHealth() == 0) {
                Game.Companion.setState(Game.STATE.FAIL);
            }
        }
    }

    public void render(Graphics g) {

            if (!attackingR && !attackingL) {
                if (!jumping) {
                    if (this.velX == 0) { // Standing still
                        if (this.facing == 1) { //facing right
                            if (this.getWeaponR() != null) { //weapon in right hand
                                if (this.getWeaponR().getWeaponId() != 2) { //weapon, but no torch right hand
                                    if (this.getWeaponL() != null) { //weapon in left hand
                                        if (this.getWeaponL().getWeaponId() != 2) { // weapon, but no torch in left hand
                                            g.drawImage(tex.getMc()[12], (int) this.x, (int) this.y, null); //no torch
                                        } else {
                                            g.drawImage(tex.getMc()[16], (int) this.x, (int) this.y, null); //torch in left hand
                                        }
                                    } else { //no weapon in left hand
                                        g.drawImage(tex.getMc()[12], (int) this.x, (int) this.y, null); //no torch
                                    }
                                } else {
                                    g.drawImage(tex.getMc()[14], (int) this.x, (int) this.y, null); //torch in right hand
                                }
                            } else { // no weapon right hand
                                if (this.getWeaponL() != null) { // weapon in left hand
                                    if (this.getWeaponL().getWeaponId() != 2) {
                                        g.drawImage(tex.getMc()[16], (int) this.x, (int) this.y, null); //torch in left hand
                                    } else {
                                        g.drawImage(tex.getMc()[12], (int) this.x, (int) this.y, null); //no torch
                                    }
                                } else { // no weapons
                                    g.drawImage(tex.getMc()[12], (int) this.x, (int) this.y, null); //no torch
                                }
                            }
                        } else { //facing left
                            if (this.getWeaponR() != null) { //weapon in right hand
                                if (this.getWeaponR().getWeaponId() != 2) { //weapon, but no torch right hand
                                    if (this.getWeaponL() != null) { //weapon in left hand
                                        if (this.getWeaponL().getWeaponId() != 2) { // wapon, but no torch in left hand
                                            g.drawImage(tex.getMc()[18], (int) this.x, (int) this.y, null); //no torch
                                        } else {
                                            g.drawImage(tex.getMc()[20], (int) this.x, (int) this.y, null); //torch in left hand
                                        }
                                    } else { //no weapon in left hand
                                        g.drawImage(tex.getMc()[18], (int) this.x, (int) this.y, null); //no torch
                                    }
                                } else {
                                    g.drawImage(tex.getMc()[22], (int) this.x, (int) this.y, null); //torch in right hand
                                }
                            } else { // no weapon right hand
                                if (this.getWeaponL() != null) { // weapon in left hand
                                    if (this.getWeaponL().getWeaponId() != 2) {
                                        g.drawImage(tex.getMc()[20], (int) this.x, (int) this.y, null); //torch in left hand
                                    } else {
                                        g.drawImage(tex.getMc()[18], (int) this.x, (int) this.y, null); //no torch
                                    }
                                } else { // no weapons
                                    g.drawImage(tex.getMc()[18], (int) this.x, (int) this.y, null); //no torch
                                }
                            }
                        }
                    } else { //moving
                        currentAnimation.drawAnimation(g, (int) x, (int) y);
                    }
                } else { // jumping
                    if (facing == 1) {
                        jumpR.drawAnimation(g, (int) this.x - 10, (int) this.y); //jumpR
                    } else {
                        jumpL.drawAnimation(g, (int) this.x - 5, (int) this.y);  //jumpL
                    }
                }
            } else if (attackingR && !attackingL) {
                if (facing == 1) {
                    if (this.getWeaponL() == null) { //attackR + nothing in left hand
                        g.drawImage(tex.getMc()[13], (int) this.x, (int) this.y, null);
                    } else {
                        if (this.getWeaponL().getType() != 2) { //attackR + weapon in left hand
                            g.drawImage(tex.getMc()[13], (int) this.x, (int) this.y, null);
                        } else { //attackR + torch in left hand
                            g.drawImage(tex.getMc()[17], (int) this.x, (int) this.y, null);
                        }
                    }
                } else {
                    if (this.getWeaponL() == null) { //attackR + nothing in left hand
                        g.drawImage(tex.getMc()[21], (int) this.x, (int) this.y, null);
                    } else {
                        if (this.getWeaponL().getType() != 2) { //attackR + weapon in left hand
                            g.drawImage(tex.getMc()[21], (int) this.x, (int) this.y, null);
                        } else { //attackR + torch in left hand
                            g.drawImage(tex.getMc()[23], (int) this.x, (int) this.y, null);
                        }
                    }
                }
            } else if (!attackingR && attackingL) {

            } else if (attackingR && attackingL) {

            }


//Collision boxes
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
//        g2d.draw(new Rectangle((int) screenXR, (int) screenYR, 1, 1));
        /*g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());*/
    }

    private void collision() {
        if (!handler.getGame().getLoading()) {
            collisionWall();
            collisionWater();
            collisionFlag();
            collisionEnemy();
            collisionMessage();
        }
    }

    public void collisionWall(){
            for (int i = 0; i < handler.getBlocks().size(); i++) {
                IBlock tempObject = handler.getBlocks().get(i);
                if (tempObject.getObjectId() == ObjectId.Wall) {
                    if (getBoundsTop().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() + (33);
                        velY = 0;
                        jumpSpeed = 0;
                        jumpCharge = false;
                    } if (getBounds().intersects(tempObject.getBounds())) {
                        y = tempObject.getY() - (height);
                        velY = 0;
                        if (falling && jumping) {
                            new Sound().play("snd/land.WAV");
                        }
                        setFalling(false);
                        setJumping(false);
                    } else {
                        falling = true; }
                        //Right
                    if (getBoundsRight().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() - (width + 2); //Strange noclip bug fixed by adding 2.
                    }
                    //Left
                    if (getBoundsLeft().intersects(tempObject.getBounds())) {
                        x = tempObject.getX() + 32; }
                }
        }
    }

    public void collisionWater(){
        for (int i = 0; i < handler.getBlocks().size(); i++) {
            IBlock tempObject = handler.getBlocks().get(i);
            if (tempObject.getObjectId() == ObjectId.Water) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    setHealth(0);
                }
            }
        }
    }

    public void collisionFlag(){
            for (int i = 0; i < handler.getTriggers().size(); i++) {
                ITrigger tempObject = handler.getTriggers().get(i);
                if (tempObject.getObjectId() == ObjectId.Flag) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.setLevel(tempObject.getDestination());
                        handler.setDN(tempObject.getDn());
                        handler.switchLevel();
                    }
                }
            }
    }

    public void collisionEnemy(){
        for (int i = 0; i < handler.getEnemies().size(); i++) {
            IEnemy tempObject = handler.getEnemies().get(i);
            if (tempObject.getObjectId() == ObjectId.Enemy) {
                Rectangle except = new Rectangle((int) (x + (width / 2) - ((width / 2) / 2)), (int) y + 10, (int) width / 2, (int) height / 2);
                if (except.intersects(tempObject.getBounds())) {
                    this.setHealth(this.getHealth() - tempObject.getBaseDamage());
                    this.lostHealth += tempObject.getBaseDamage();
                    this.setInvulnerable(30);
                    new Sound().play("snd/ouch.WAV");
                }
            }
        }
    }

    public void collisionMessage(){
        for (int i = 0; i < handler.getTriggers().size(); i++) {
            ITrigger tempObject = handler.getTriggers().get(i);
            if (tempObject.getObjectId() == ObjectId.Message) {
                if (this.getBounds().intersects(tempObject.getBounds()) || tempObject.getBounds().contains(this.getBounds())) {
                    tempObject.setActive(true);
                } else {
                    tempObject.setActive(false);
                }
            }
        }
    }

///////////////////////////////////////////GETTERS AND SETTERS//////////////////
    public void setAttackDuration(Weapon weapon) {
        this.attackDuration = weapon.getDuration() + 5;
    }

    public void setJumpCharge(boolean set) {
        jumpCharge = set;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2)), (int) (y + (height / 2)), (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (x + width - 5), (int) y + 5, 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, 5, (int) height - 10);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public Texture getTexture() {
        return this.tex;
    }

    public float getScreenX() {
        return this.screenX;
    }

    public float getScreenY() {
        return this.screenY;
    }

    public Weapon getWeaponR() {
        return this.weaponR;
    }

    public void setWeaponR(Weapon weapon) {
        this.weaponR = weapon;
    }

    public Weapon getWeaponL() {
        return this.weaponL;
    }

    public void setWeaponL(Weapon weapon) {
        this.weaponL = weapon;
    }

    public Animation getCurrentAnimation() {
        return this.currentAnimation;
    }

}
