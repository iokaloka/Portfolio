package guiltspawn.graphics;

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

}
