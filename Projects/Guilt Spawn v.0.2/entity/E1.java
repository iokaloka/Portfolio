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
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;

public class E1 extends Enemy {

    private boolean attacking, damaged, screeching = false;
    private Sprite s;
    private Animation ani;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        4, 5, 5
    };
    private int aggroCD = 120;

    //animation actions
    private static final int WALKING = 0;
    private static final int ATTACKING = 1;
    private static final int JUMPING = 2;

    public E1(TileMap tm) {
        super(tm);
        attacking = false;
        damaged = false;
        aggroed = false;
        jumping = false;
        moveSpeed = 0.4;
        maxSpeed = 6.0;
        fallSpeed = 0.15;
        maxFallSpeed = 15.0;
        jumpStart = -4.0;
        stopJumpSpeed = 0.8;
        width = 96;
        height = 96;
        cwidth = 96;
        cheight = 96;
        health = maxHealth = 3;
        damage = 10;
        s = new Sprite("/sprites/enemies/e1.png");
        ani = new Animation();
        sprites = new ArrayList<>();
        right = true;
        facingRight = true;
        this.currentAction = WALKING;
        //load sprites here;
        loadAnimationSprites();
        ani.setAnimation(sprites.get(WALKING));
        ani.setSpeed(8);
    }

    public void loadAnimationSprites() {
        try {
            for (int i = 0; i < 3; i++) {
                BufferedImage bi[] = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    bi[j] = (BufferedImage) s.sub(i, j, 96, 96);
                }
                sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        attacking = false;
        damaged = false;
        aggroed = false;
        aggroCD = 60;
        jumping = false;
        moveSpeed = 0.4;
        maxSpeed = 6.0;
        fallSpeed = 0.15;
        maxFallSpeed = 15.0;
        jumpStart = -4.0;
        stopJumpSpeed = 0.8;
        dx += moveSpeed;
        if (facingRight) {
            left = false;
            right = true;
        } else {
            left = true;
            right = false;
        }
    }

    public void checkAggro(Player player) {
        int px = player.getX();
        int ex = this.getX();
        int py = player.getY();
        int ey = this.getY();
        int a = min(px, ex);
        int b = max(px, ex);
        int c = min(py, ey);
        int d = max(py, ey);
        if (b - a < 200 && d - c < 200) {
            aggroed = true;
            if (tileMap.getGame().getHandler().getPlayer().getX() < this.getX()) {
                facingRight = false;
                left = true;
                right = false;
            } else {
                facingRight = true;
                left = false;
                right = true;
            }
        }

    }

    @Override
    public boolean intersects(MapObject o) {
        Rectangle r1 = this.getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) getX() - (cwidth / 2), (int) getY() - 12, cwidth, cheight);
    }

    public void attack() {
        this.attacking = true;
        SoundEffect.E1GROWL1.play();
        this.dx = 0;
        this.moveSpeed = 0;
        this.maxSpeed = 10.0;
        ani.reset();
    }

    public void checkPlayerCollision(Player player) {
        if (!damaged) {
            if (this.intersects(player)) {
                player.hit(damage);
                SoundEffect.OUCH.play();
                SoundEffect.SCRATCH1.play();
                damaged = true;
            }
        }

    }

    private void getNextPosition() {
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

    @Override
    public void tick() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check aggron and try to attack
        if (!aggroed && aggroCD == 0) {
            checkAggro(tileMap.getGame().getHandler().getPlayer());
            if (aggroed) {
                attack();
            }
        } else if (!aggroed && aggroCD > 0) {
            aggroCD--;
            if (aggroCD < 0) {
                aggroCD = 0;
            }
        }

        //handle attacking
        if (attacking) {
            if (ani.hasPlayedOnce()) {
                this.attacking = false;
                this.jumping = true;
                if (!screeching) {
                    SoundEffect.E1CREECH1.play();
                    screeching = true;
                }
                moveSpeed = 0.8;
                ani.reset();
            }
        }

        //handle jumping TODO
        if (jumping && !ani.hasPlayedOnce()) {
            checkPlayerCollision(tileMap.getGame().getHandler().getPlayer());
            screeching = false;
        }

        if (right && dx == 0 && !aggroed) {
            right = false;
            left = true;
            facingRight = false;
            setPosition(xtemp, ytemp);
        } else if (left && dx == 0 && !aggroed) {
            right = true;
            left = false;
            facingRight = true;
            setPosition(xtemp, ytemp);
        }

        if (attacking) {
            if (currentAction != ATTACKING) {
                currentAction = ATTACKING;
                ani.reset();
                ani.setAnimation(sprites.get(ATTACKING));
                ani.setSpeed(12);
            }
        } else if (jumping) {
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                ani.setAnimation(sprites.get(JUMPING));
                ani.setSpeed(10);
                ani.reset();
            }
        } else if (left || right) {
            if (currentAction != WALKING) {
                reset();
                currentAction = WALKING;
                ani.setAnimation(sprites.get(WALKING));
                ani.setSpeed(8);
            }
        }
        ani.tick();

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setMapPosition();
        g2.setColor(Color.BLUE);
        if (facingRight) {
            //g2.draw(getRectangle());
            g.drawImage(this.ani.getImage(), (int) getX() - (cwidth / 2), (int) getY() - 12, cwidth, cheight, null);
        } else {
            //g2.draw(getRectangle());
            g.drawImage(this.ani.getImage(), (int) getX() + (cwidth / 2), (int) getY() - 12, -cwidth, cheight, null);
        }
        g2.setColor(Color.YELLOW);
        //g2.drawRect(getX(), getY(), 2, 2);
    }

}
