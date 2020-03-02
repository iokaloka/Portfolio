package ti4v;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hud {
    
    private Player p;
    private BufferedImage bg;
    private Font f1;
    
    public Hud(Player p){
        this.p = p;
        this.bg = fetch("/playerHud.png");
        this.f1 = new Font("Arial Black", Font.BOLD, 20);
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
    
    public void render(Graphics g){
        g.drawImage(bg, 0, 0, null);
        g.drawImage(this.p.getFaction().getSheet(), 367, 71, 904, 615, null);
        g.drawImage(this.p.getCommandSheet(), 1271, 67, 629, 620, null);
        g.drawImage(this.p.getReinforcements(), 1234, 750, null);
        g.drawImage(p.getFaction().getFt1().getIcon(), 27, 19, 318, 212, null);
        g.drawImage(p.getFaction().getFt2().getIcon(), 27, 233, 318, 212, null);
        g.drawImage(this.p.getFaction().getFp().getIcon(), 364, 752, null);
        renderReserveAmounts(g);
    }
    
    public void renderReserveAmounts(Graphics g){
        g.setColor(Color.white);
        g.setFont(f1);
        g.drawString("x " + this.p.getReservedDN(), 1280, 855);
        g.drawString("x " + this.p.getReservedWarSun(), 1420, 855);
        g.drawString("x " + this.p.getReservedFlagShip(), 1550, 855);
        g.drawString("x " + this.p.getReservedFighter(), 1690, 855);
        g.drawString("x " + this.p.getReservedDestroyer(), 1830, 855);
        g.drawString("x " + this.p.getReservedCarrier(), 1280, 1035);
        g.drawString("x " + this.p.getReservedCruiser(), 1420, 1035);
        g.drawString("x " + this.p.getReservedInfantry(), 1550, 1035);
        g.drawString("x " + this.p.getReservedSpaceDock(), 1690, 1035);
        g.drawString("x " + this.p.getReservedPDS(), 1830, 1035);
        g.drawString(this.p.getControlTokens() + " x", 1742, 920);
        g.drawString("x " + this.p.getCommandTokens(), 1350, 920);
        renderFactionSpecificTokens(g);
        renderPlanets(g);
    }
    
    public void renderPlanets(Graphics g){
        for(int i = 0; i < this.p.getPlanets().size(); i++){
            g.drawImage(this.p.getPlanets().get(i).getCard(), 600 + i * 150, 755, (int)this.p.getPlanets().get(i).getCard().getWidth() / 3, (int)this.p.getPlanets().get(i).getCard().getHeight() / 3,null);
        }
    }
    
    public void renderFactionSpecificTokens(Graphics g){
        if(this.p.getFaction().getName().equals("The Naalu Collective")){
            g.drawImage(this.p.getFaction().getNaalu0().getIcon(), 35, 950, 100, 100, null);
        } else if (this.p.getFaction().getName().equals("The Ghost of Creuss")) {
            g.drawImage(this.p.getFaction().getCreussA().getIcon(), 35, 950, 100, 100, null);
            g.drawImage(this.p.getFaction().getCreussB().getIcon(), 145, 950, 100, 100, null);
        } else if (this.p.getFaction().getName().equals("The Nekro Virus")) {
            g.drawImage(this.p.getFaction().getNekroX().getIcon(), 35, 950, 100, 70, null);
            g.drawImage(this.p.getFaction().getNekroY().getIcon(), 145, 950, 100, 70, null);
        }
    }
    
////////////////////////////////GETTERS AND SETTERS/////////////////////////////
    
}
