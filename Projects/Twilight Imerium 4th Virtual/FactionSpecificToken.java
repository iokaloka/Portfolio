package ti4v;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FactionSpecificToken {

    private BufferedImage icon;
    private int type;
    private String tokenName;

    public FactionSpecificToken(int type) {
        this.type = type;
        switch (type) {
            case 0: //Naalu "0"
                this.icon = fetch("/naalu0.png");
                this.tokenName = "naalu0";
                break;
            case 1: //Creuss A
                this.icon = fetch("/creussA.png");
                this.tokenName = "creussA";
                break;
            case 2: //Creuss B
                this.icon = fetch("/creussB.png");
                this.tokenName = "creussB";
                break;
            case 3: //Nekro X
                this.icon = fetch("/nekroX.png");
                this.tokenName = "nekroX";
                break;
            case 4: //Nekro Y
                this.icon = fetch("/nekroY.png");
                this.tokenName = "nekroY";
                break;

        }
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

    public void takeEffect() {
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    
/////////////////////////////////GETTERS AND SETTERS////////////////////////////

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
    
    

}
