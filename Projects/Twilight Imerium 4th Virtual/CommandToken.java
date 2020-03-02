package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CommandToken {

    private BufferedImage icon;
    private Player player;

    public CommandToken(Player p) {
        this.player = p;
        switch (this.player.getColor()) {
            case "Red":
                icon = fetch("/ctRed.png");
                break;
            case "Blue":
                icon = fetch("/ctBlue.png");
                break;
            case "Green":
                icon = fetch("/ctGreen.png");
                break;
            case "Yellow":
                icon = fetch("/ctYellow.png");
                break;
            case "Purple":
                icon = fetch("/ctPurple.png");
                break;
            case "Black":
                icon = fetch("/ctBlack.png");
                break;
        }
    }

    public BufferedImage fetch(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println("Couldn't fetch commandtoken icon img.");
        }
        return img;
    }

    public void render(Graphics g) {

    }

}
