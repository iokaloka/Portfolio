package guiltspawn.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import guiltspawn.entity.Player;
import java.awt.Color;
import static java.lang.Integer.min;

public class HUD {
    
    private BufferedImage img;
    private Player player;
    
    public HUD(Player player){
        this.player = player;
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/interface/hud.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void render(Graphics g){
        int sanity = min((int)((double)531 * ((double)player.getSanity() / (double)player.getMaxSanity())), 531);
        int fatigue = min((int)((double)439 * ((double)player.getFatigue() / (double)player.getMaxFatigue())), 439);
        int drive = min((int)((double)169 * ((double)player.getDrive() / (double)player.getMaxDrive())), 169);
        g.drawImage(img, -30, -30, null);
        g.setColor(Color.GREEN);
        g.fillRect(120, 94, sanity, 6);
        g.setColor(Color.YELLOW);
        g.fillRect(212, 112, fatigue, 6);
        g.setColor(Color.MAGENTA);
        g.fillRect(94, 120, 5, drive);
    }
    
}
