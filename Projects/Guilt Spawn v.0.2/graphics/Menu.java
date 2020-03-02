package guiltspawn.graphics;

import guiltspawn.engine.GuiltSpawn;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Menu {

    private BufferedImage img, f1, f2;
    private int fOffX, fOffY, offTimer = 0, smokeTimer = 0, ashTimer = 0, emberTimer = 0;
    private Random r;
    private GuiltSpawn game;
    private ArrayList<Particle> particles;

    public Menu(GuiltSpawn game) {
        fOffX = 480;
        fOffY = 270;
        particles = new ArrayList<>();
        r = game.getHandler().getTm().getR();
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/menu/menu1.png"));
            f1 = ImageIO.read(getClass().getResourceAsStream("/menu/menuf1.png"));
            f2 = ImageIO.read(getClass().getResourceAsStream("/menu/menuf2.png"));
        } catch (Exception e) {
            System.out.println("Couldn't load menu-image");
        }
        this.game = game;
    }
    
    public void clearParticles(){
        this.particles.clear();
    }

    public void tick() {
        offTimer++;
        smokeTimer++;
        ashTimer++;
        emberTimer++;
        if (offTimer >= 5) {
            fOffX = r.nextInt(540);
            fOffY = r.nextInt(270);
            offTimer = 0;
        }
        if (smokeTimer >= 10) {
            smokeTimer = 0;
            Particle p = new Particle("/menu/smoke1.png", 0, r.nextInt(1920) - 50, 1080 + 50, r.nextInt(3) - 1, -1 - (r.nextInt(4)));
            p.setSize(50 - (r.nextInt(16)));
            particles.add(p);
        }
        if (ashTimer >= 5) {
            ashTimer = 0;
            Particle p = new Particle("/menu/ash1.png", 1, r.nextInt(2100) - 50, -5, -r.nextInt(3), 1 + r.nextInt(4));
            p.setSize(r.nextInt(3) + 1);
            particles.add(p);
        }
        if(emberTimer >= 20){
            emberTimer = 0;
            Particle p = new Particle(2, 960 + r.nextInt(1440), 1080, -2 -r.nextInt(8), -5 -r.nextInt(5));
            p.setSize(3 + r.nextInt(8));
            particles.add(p);
        }

        for (int i = 0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.tick();

            if (p.isShouldRemove()) {
                particles.remove(p);
                i--;
            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.getWindow().getScreen().width, game.getWindow().getScreen().height, null);
        g.drawImage(f1, fOffX, fOffY, game.getWindow().getScreen().width, game.getWindow().getScreen().height, null);
        g.drawImage(f2, 0 - fOffX, 0 - fOffY, game.getWindow().getScreen().width, game.getWindow().getScreen().height, null);
        for (Particle p : particles) {
            p.render(g);
        }
        renderTexts(g);
    }

    public void renderTexts(Graphics g) {
        Font font = new Font("Castellar", Font.PLAIN, 22);
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);
        g.drawString("Start game", 1000, 470);
        g.drawString("Continue", 1000, 505);
        g.drawString("Load", 1000, 540);
        g.drawString("Options", 1000, 575);
        g.drawString("Exit", 1000, 610);
        g.setFont(new Font("Castellar", Font.PLAIN, 38));
        g.drawString("Guilt Spawn", 380, 552);
//        g2d.drawRect(990, 445, 175, 32);
//        g2d.drawRect(990, 480, 150, 32);
//        g2d.drawRect(990, 515, 90, 32);
//        g2d.drawRect(990, 550, 125, 32);
//        g2d.drawRect(990, 585, 75, 32);
    }

}
