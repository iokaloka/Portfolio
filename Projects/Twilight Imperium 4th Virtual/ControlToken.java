package ti4v;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ControlToken {
    
    private BufferedImage icon;
    private Player player;
    
    public ControlToken(Player p){
        this.player = p;
        switch (this.player.getColor()) {
            case "Red":
                icon = fetch("/controlRed.png");
                break;
            case "Blue":
                icon = fetch("/controlBlue.png");
                break;
            case "Green":
                icon = fetch("/controlGreen.png");
                break;
            case "Yellow":
                icon = fetch("/controlYellow.png");
                break;
            case "Purple":
                icon = fetch("/controlPurple.png");
                break;
            case "Black":
                icon = fetch("/controlBlack.png");
                break;
        }
    }
    
    public BufferedImage fetch(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println("Couldn't fetch controltoken icon img.");
        }
        return img;
    }
    
}
