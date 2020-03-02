package ti4v;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mainmenu {

    private TI4V game;
    private BufferedImage bg;
    private BufferedImage playerAmount;
    private Font font1;
    private Font font2;
    private Font font3;

    public Mainmenu(TI4V game) {
        this.game = game;
        this.font1 = new Font("Castellar", Font.PLAIN, 120);
        this.font2 = new Font("Arial Black", Font.PLAIN, 20);
        this.font3 = new Font("Castellar", Font.PLAIN, 36);
        this.bg = fetch("/bg.png");
        this.playerAmount = fetch("/playerAmount.png");
    }

    public BufferedImage fetch(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println("Image couldn't be fetched from: " + path);
        }
        return img;
    }

    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.yellow);
        g.setFont(font1);
        String s = "Twilight Imperium";
        g.drawString(s, 960 - (g.getFontMetrics().stringWidth(s) / 2), 300);
        s = "VIRTUAL based on 4th edition";
        g.setFont(font2);
        g.drawString(s, 285, 340);
        if (mouseOverStart()) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        s = "Start game";
        g.setFont(font3);
        g.drawString(s, 960 - (g.getFontMetrics().stringWidth(s) / 2), 700);
        g.drawImage(playerAmount, 960 - (playerAmount.getWidth() / 2), 700, null);
        g.setColor(Color.white);
        g.drawString("" + this.game.getPlayers(), 952, 843);
    }

    public boolean mouseOverStart() {
        int startX = this.game.getMm().getX();
        int startY = this.game.getMm().getY();
        if (startX > 805 && startX < 1110 && startY > 645 && startY < 725) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean mouseOverMore(){
        int startX = this.game.getMm().getX();
        int startY = this.game.getMm().getY();
        if (startX > 1015 && startX < 1037 && startY > 807 && startY < 851) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean MouseOverLess(){
        int startX = this.game.getMm().getX();
        int startY = this.game.getMm().getY();
        if (startX > 883 && startX < 904 && startY > 807 && startY < 851) {
            return true;
        } else {
            return false;
        }
    }
    
/////////////////////////////GETTERS AND SETTERS////////////////////////////////

    public TI4V getGame() {
        return game;
    }

    public void setGame(TI4V game) {
        this.game = game;
    }

    public BufferedImage getBg() {
        return bg;
    }

    public void setBg(BufferedImage bg) {
        this.bg = bg;
    }

    public Font getFont1() {
        return font1;
    }

    public void setFont1(Font font1) {
        this.font1 = font1;
    }

    public Font getFont2() {
        return font2;
    }

    public void setFont2(Font font2) {
        this.font2 = font2;
    }

    public Font getFont3() {
        return font3;
    }

    public void setFont3(Font font3) {
        this.font3 = font3;
    }
    
    

}
