package guiltspawn.entity;

import guiltspawn.engine.TileMap;
import guiltspawn.graphics.Animation;
import guiltspawn.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class E2 extends Enemy {

    private Sprite s;
    private Animation ani;
    private BufferedImage[] sprites;
    private int timer = 0;

    public E2(TileMap tm) {
        super(tm);
        moveSpeed = 0.2;
        maxSpeed = 6.0;
        fallSpeed = 0.15;
        maxFallSpeed = 15.0;
        width = 96;
        height = 96;
        cwidth = 96;
        cheight = 96;
        health = maxHealth = 3;
        damage = 1;
        s = new Sprite("/sprites/enemies/e2.png");
        ani = new Animation();
        right = true;
        facingRight = true;
        //load sprites here;
        loadSprites();
    }

    public void loadSprites() {
        sprites = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            sprites[i] = (BufferedImage)s.sub(0, i, width, height);
        }
        ani = new Animation();
        ani.setAnimation(sprites);
        ani.setSpeed(8);
    }
    
    @Override
    public boolean intersects(MapObject o){
        Rectangle r1 = this.getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }
    
    @Override
    public Rectangle getRectangle(){
        return new Rectangle((int) getX() - (cwidth / 2), (int) getY() - 30, cwidth, cheight);
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
        } else {
            dx = 0;
        }
        //falling
        if (falling) {
            dy += fallSpeed;
        }

    }

    @Override
    public void tick() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        timer++;

        if (right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
            setPosition(xtemp, ytemp);
        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;
            setPosition(xtemp, ytemp);
        }
        if (dx != 0) {
            ani.tick();
        }

        if (timer >= 30) {
            timer = 0;
            if (!left && !right) {
                int i = tileMap.getR().nextInt(2);
                switch (i) {
                    case 0:
                        left = true;
                        facingRight = false;
                        break;
                    case 1:
                        right = true;
                        facingRight = true;
                        break;
                }
            } else {
                left = false;
                right = false;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        setMapPosition();
        g2.setColor(Color.BLUE);
        if (facingRight) {
            g2.draw(getRectangle());
            g.drawImage(this.ani.getImage(), (int) getX() - (cwidth / 2), (int) getY() - 30, cwidth, cheight, null);
        } else {
            g2.draw(getRectangle());
            g.drawImage(this.ani.getImage(), (int) getX() + (cwidth / 2), (int) getY() - 30, -cwidth, cheight, null);
        }
        g2.setColor(Color.YELLOW);
        g2.drawRect(getX(), getY(), 2, 2);
    }

}
