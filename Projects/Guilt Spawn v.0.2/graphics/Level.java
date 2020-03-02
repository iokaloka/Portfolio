package guiltspawn.graphics;

import guiltspawn.engine.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Level {

    private float dist, maxDist;
    private Handler h;
    private BufferedImage map;
    private BufferedImage[] gr;
    private Random r;

    public Level(int i, int j, Handler h) {
        this.h = h;
        gr = new BufferedImage[7];
        loadMap(i, j);
    }

    public void loadMap(int i, int j) {
        try {
            this.map = ImageIO.read(getClass().getResourceAsStream("/level" + i + "/r" + j + "/g0.png"));
            for (int k = 0; k < 7; k++) {
                gr[k] = ImageIO.read(getClass().getResourceAsStream("/level" + i + "/r" + j + "/g" + (k + 1) + ".png"));
            }
            System.out.println("MapImage loaded for /level" + i + "/" + j + ".png");
        } catch (Exception e) {
            System.out.println("Level path: /level" + i + "/" + j + ".png");
        }
    }

    public void countDistance(int min, int max) {
        dist = h.getPlayer().getX() - ((max + min) / 2);
        countMaxDist(min, max);
    }

    public void countMaxDist(int min, int max) {
        maxDist = (h.getMaxX() + h.getMinX() / 2) - h.getMinX();
    }

    public int countBgOffset() {
        return countMiddlePosition() - (gr[6].getWidth() / 2);
    }

    public int countMiddlePosition() {
        return (h.getMaxX() + h.getMinX()) / 2;
    }

    public int countBgMovement() {
        return countBgOffset() - h.getMinX() + 720;
    }

    public int countMaxPlayerOffset() {
        return countMiddlePosition() - h.getMinX();
    }

    public boolean shouldMove() {
        if (dist < countMaxPlayerOffset()) {
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
        countDistance(h.getMinX(), h.getMaxX());
    }

    public void renderBack(Graphics g) {
        if (gr[6] != null) {
            if (shouldMove()) {
                g.drawImage(gr[6], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * 0.3), 0, null);
            }
            g.setColor(Color.GREEN);
            g.drawString("" + dist, h.getPlayer().getX() - 50, h.getPlayer().getY() - 50);
        }

        if (gr[5] != null) {
            if (shouldMove()) {
                g.drawImage(gr[5], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * 0.2), 0, null);
            }
        }

        if (gr[4] != null) {
            if (shouldMove()) {
                g.drawImage(gr[4], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * 0.1), 0, null);
            }
        }

        if (gr[3] != null) {
            if (shouldMove()) {
                g.drawImage(gr[3], 0, 0, null);
            }
        }

    }

    public void renderFront(Graphics g) {
        if (gr[2] != null) {
            g.drawImage(gr[2], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * -0.1), 0, null);
//            g.drawImage(gr[2], (int) (-100 - (100 * ((double) dist / (double) 2048))), (int) 0, null);
        }

        if (gr[1] != null) {
            g.drawImage(gr[1], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * -0.2), 0, null);
//            g.drawImage(gr[1], (int) (-200 - (200 * ((double) dist / (double) 2048))), (int) 0, null);
        }

        if (gr[0] != null) {
            g.drawImage(gr[0], (int) ((countBgOffset() + ((double) countBgMovement() * ((double) dist / countMaxPlayerOffset()))) * -0.3), 0, null);
//            g.drawImage(gr[0], (int) (-300 - (300 * ((double) dist / (double) 2048))), (int) 0, null);
        }
    }

    public void renderEffects(Graphics g) {

    }

    //////////////////////////GETTERS AND SETTERS///////////////////////////////
    public BufferedImage getMap() {
        return map;
    }

    public void setMap(BufferedImage map) {
        if (map == null) {
            System.out.println("Map is null");
        }
        this.map = map;
    }

}
