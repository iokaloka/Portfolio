package guiltspawn.entity;

import guiltspawn.engine.TileMap;
import guiltspawn.graphics.Animation;
import guiltspawn.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.lang.Integer.min;
import java.util.ArrayList;

public class Player extends MapObject {

    //player stuff
    private int sanity, maxSanity, fatigue, maxFatigue, drive, maxDrive;
    private int fatigueTimer = 0;
    private boolean dead, stabilizing;

    //attacking
    private boolean attacking;
    private int attackDamage, attackRange, attackCost;
    private ArrayList<Weapon> weapons;

    //dodging
    private boolean dodging;

    //animation
    private Sprite s;
    private Animation ani;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        2, 6, 2, 1, 2, 1
    };

    //animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int ATTACKING = 4;
    private static final int DODGING = 5;

    public Player(TileMap tm) {
        super(tm);
        width = 48; //FOR READING OF SPRITES
        height = 96; //FOR READING OF SPRITES
        cwidth = 48; //actual width
        cheight = 96; //actual height
        moveSpeed = 0.3;
        maxSpeed = 10.0;
        stopSpeed = 0.8;
        fallSpeed = 0.24;
        maxFallSpeed = 15.0;
        jumpStart = -8.4;
        stopJumpSpeed = 1.2;
        facingRight = true;
        sanity = 10;
        maxSanity = 100;
        drive = 25;
        maxDrive = 50;
        fatigue = 0;
        maxFatigue = 100;
        //attack
        attackCost = 20;
        attackDamage = 1;
        attackRange = 40;
        weapons = new ArrayList<>();
        //LOAD SPRITES HERE!
        ani = new Animation();
        sprites = new ArrayList<>();
        s = new Sprite("/sprites/mc/mc.png");
        loadAnimationSprites();
        currentAction = FALLING;
    }

    public void getNextPosition() {
        // movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        // cannot move while attacking, except in air
        if ((currentAction == ATTACKING) && !(jumping || falling)) {
            dx = 0;
        }
        // jumping
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }
        // falling
        if (falling) {
            dy += fallSpeed;
            if (dy > 0) {
                jumping = false;
                ani.reset();
            }
            if (dy < 0 && !jumping) {
                dy += stopJumpSpeed;
            }
            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
    }

    public void loadAnimationSprites() {
        try {
            for (int i = 0; i < 5; i++) {
                BufferedImage bi[] = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    bi[j] = s.sub(i, j, 77, 96);
                }
                sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hit(int damage){
        sanity -= damage;
        if(sanity < 0) sanity = 0;
        if(sanity == 0) dead = true;
        drive += damage / 2;
        if(drive > 50) drive = 50;
    }
    
    public void reset(){
        moveSpeed = 0.3;
        maxSpeed = 10.0;
        stopSpeed = 0.8;
        fallSpeed = 0.24;
        maxFallSpeed = 15.0;
        jumpStart = -8.4;
        stopJumpSpeed = 1.2;
        facingRight = true;
        sanity = 55;
        maxSanity = 100;
        drive = 22;
        maxDrive = 50;
        fatigue = 0;
        maxFatigue = 100;
        dead = false;
    }

    public void tick() {
        if (!this.getTileMap().getGame().getHandler().getLoading()) {
            getNextPosition();
            checkTileMapCollision();
            setPosition(xtemp, ytemp);

            //update fatigue
            if (fatigue > 0) {
                fatigueTimer++;
                if (fatigueTimer >= min((int)((double)10 - ((double)6 * ((double)drive / (double)maxDrive))), 10)) {
                    fatigue--;
                    fatigueTimer = 0;
                }
                if (fatigue > maxFatigue) {
                    fatigue = maxFatigue;
                }
            }

            //update drive
            if (stabilizing) {
                if (drive >= 1) {
                    drive--;
                    sanity++;
                }
            }

            //check if attack has stopped
            if (currentAction == ATTACKING) {
                if (ani.hasPlayedOnce()) {
                    attacking = false;
                }
            }

            //attack
            if (attacking && currentAction != ATTACKING) {
                Weapon w = new Weapon(tileMap);
                weapons.add(w);
                fatigue += 15;
            }
            //update attack array
            for (int i = 0; i < weapons.size(); i++) {
                weapons.get(i).tick();
                if (weapons.get(i).getAnimation().hasPlayedOnce()) {
                    weapons.remove(i);
                    i--;
                }
            }

            //set animation here
            if (attacking) {
                if (currentAction != ATTACKING) {
                    currentAction = ATTACKING;
                    ani.reset();
                    ani.setAnimation(sprites.get(ATTACKING));
                    ani.setSpeed(10);
                }
            } else if (dodging) {
                if (currentAction != DODGING) {
                    currentAction = DODGING;
                    //animation.setFrames(sprites.get(DODGING);
                    //animation.setDealy(100);
                    //width = 30;
                }
            } else if (dy > 0) {
                if (currentAction != FALLING) {
                    currentAction = FALLING;
                    ani.setAnimation(sprites.get(FALLING));
                    ani.setSpeed(-1);
                }
            } else if (dy < 0) {
                if (currentAction != JUMPING) {
                    currentAction = JUMPING;
                    ani.setAnimation(sprites.get(JUMPING));
                    ani.setSpeed(10);
                    ani.reset();

                }
            } else if (left || right) {
                if (currentAction != WALKING) {
                    currentAction = WALKING;
                    ani.setAnimation(sprites.get(WALKING));
                    ani.setSpeed(7);
                }
            } else if (currentAction != IDLE) {
                currentAction = IDLE;
                ani.setAnimation(sprites.get(IDLE));
                ani.setSpeed(30);
            }
            if (currentAction != JUMPING && currentAction != FALLING) {
                ani.tick();
            } else if (currentAction == JUMPING) {
                ani.tickTillEnd();
            }

            //set direction
            if (currentAction != ATTACKING) {
                if (right) {
                    facingRight = true;
                }
                if (left) {
                    facingRight = false;
                }
            }
        }
        if(dead){
            tileMap.getGame().getHandler().gameOver();
        }
    }

    public void render(Graphics g) {
        setMapPosition();
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(getRectangle());
        if (facingRight) {
            g.drawImage(ani.getImage(), (int) getX() - (width - 10), (int) getY() - height / 2, 77, 96, null);
        } else {
            g.drawImage(ani.getImage(), (int) getX() + (width - 10), (int) getY() - height / 2, -77, 96, null);
        }

        for (int i = 0; i < weapons.size(); i++) {
            weapons.get(i).render(g);
        }
        //g.drawRect(getX(), getY(), 2, 2);
    }

    /////////////////////////GETTERS AND SETTERS////////////////////////////////
    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getDrive() {
        return drive;
    }

    public void setDrive(int drive) {
        this.drive = drive;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isDodging() {
        return dodging;
    }

    public void setDodging(boolean dodging) {
        this.dodging = dodging;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public int getMaxSanity() {
        return maxSanity;
    }

    public void setMaxSanity(int maxSanity) {
        this.maxSanity = maxSanity;
    }

    public int getMaxFatigue() {
        return maxFatigue;
    }

    public void setMaxFatigue(int maxFatigue) {
        this.maxFatigue = maxFatigue;
    }

    public int getMaxDrive() {
        return maxDrive;
    }

    public void setMaxDrive(int maxDrive) {
        this.maxDrive = maxDrive;
    }

    public boolean isStabilizing() {
        return stabilizing;
    }

    public void setStabilizing(boolean stabilizing) {
        this.stabilizing = stabilizing;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackCost() {
        return attackCost;
    }

}
