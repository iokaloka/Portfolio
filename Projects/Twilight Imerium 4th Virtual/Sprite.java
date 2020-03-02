package ti4v;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage img;

    public Sprite(String path) {
        try {
            this.img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Sprite path not found");
        }
    }

    public BufferedImage sub(int row, int col, int width, int height) {
        return img.getSubimage(0 + (col * width), 0 + (row * height), width, height);
    }
    
    public BufferedImage unit(int row, int col){
        return img.getSubimage(col * 119, row * 119, 119, 119);
    }
    
    public BufferedImage icon(int factionNumber){
        return img.getSubimage(0, 127 * factionNumber, 368, 127);
    }
    
    public BufferedImage icon2(int factionNumber){
        return img.getSubimage(0, 127 * factionNumber, 183, 127);
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
    
    

}
