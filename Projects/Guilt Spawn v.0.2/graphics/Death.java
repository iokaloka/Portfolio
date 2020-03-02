package guiltspawn.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Death {
    
    private BufferedImage img;
    
    public Death(){
        try{
            this.img = ImageIO.read(getClass().getResourceAsStream("/death/death1.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void render(Graphics g){
        g.drawImage(img, 0, 0, null);
        g.setColor(Color.WHITE);
//        g.drawRect(825, 795, 200, 50);
//        g.drawRect(825, 860, 200, 50);
    }
    
}
