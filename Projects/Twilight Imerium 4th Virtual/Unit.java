package ti4v;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Unit {

    private BufferedImage icon;
    private int typeNumber;
    
    public void render(Graphics g, int x, int y){
        g.drawImage(this.icon, x, y, 94, 94, null);
    }
    
/////////////////////////////////GETTERS AND SETTERS////////////////////////////

    public int getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(int typeNumber) {
        this.typeNumber = typeNumber;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }
    
}
