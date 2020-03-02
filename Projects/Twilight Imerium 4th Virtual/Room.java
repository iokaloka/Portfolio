package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Room {
    
    private BufferedImage bg;
    private BufferedImage grid;
    
    public Room(){
        bg = fetch("/bg.png");
        grid = fetch("/galaxyTemplate4plusGrid.png");
    }
    
    public BufferedImage fetch(String path){
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
        g.drawImage(grid, 960 - grid.getWidth() / 2, 540 - grid.getHeight() / 2 + 40, null);
    }

}
