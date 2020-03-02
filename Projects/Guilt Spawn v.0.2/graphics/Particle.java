package guiltspawn.graphics;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class Particle {

    private BufferedImage img;
    private int x, y, rx, ry, size, speedX, speedY, type, criticalHeight, burnTimer = 0;
    private boolean shouldRemove = false;
    private Animation ani;
    private Sprite s;
    private BufferedImage[] images;

    public Particle(int type, int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.type = type;
        this.size = 5;
        try {
            if (type == 2) {
                ani = new Animation();
                s = new Sprite("/menu/flame1.png");
                images = new BufferedImage[8];
                loadImages();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Particle(String path, int type, int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.type = type;
        size = 1;
        if (type == 0) {
            criticalHeight = 0;
        } else if (type == 1) {
//            criticalHeight = 1080;
            criticalHeight = 4096;
        }
        try {
            this.img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadImages() {
        for (int i = 0; i < 8; i++) {
            images[i] = (BufferedImage)(s.sub(0, i, 48, 48));
        }
        ani.setAnimation(images);
        ani.setSpeed(2);
        img = ani.getImage();
    }

    public void tick() {
        if (type == 0) {
            if (!shouldRemove) {
                this.size++;
            }
            this.y += speedY;
            this.x += speedX;
            if (this.y <= criticalHeight - size || size > 500) {
                shouldRemove = true;
            }
        } else if (type == 1) {
            this.y += speedY;
            this.x += speedX;
            if (this.y >= criticalHeight) {
                shouldRemove = true;
            }
        } else if (type == 2) {
            burnTimer++;
            ani.tick();
            img = ani.getImage();
            this.y += speedY + ry;
            this.x += speedX + rx;
            if (burnTimer >= 30 && size > 0) {
                burnTimer = 0;
                size--;
            }
            if (this.size <= 0) {
                shouldRemove = true;
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, x, y, size, size, null);
    }

    //////////////////////////GETTERS AND SETTERS///////////////////////////////
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isShouldRemove() {
        return this.shouldRemove;
    }

    public void setShouldRemove(boolean shouldRemove) {
        this.shouldRemove = shouldRemove;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
