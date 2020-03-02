package guiltspawn.entity;

import guiltspawn.engine.TileMap;
import java.awt.Color;
import java.awt.Graphics;

public class Block extends MapObject{

    private double x, y;
    private int type;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    public Block(TileMap tm, double x, double y, int type) {
        super(tm);
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public void tick(){}

    @Override
    public void render(Graphics g) {
        if (this.type == 0) {
//            g.setColor(Color.BLUE);
//            g.drawRect((int)x, (int)y, 32, 32);
        } else {
            g.setColor(Color.RED);
            g.drawRect((int)x, (int)y, 32, 32);
        }
        
    }

    ///////////////////////////GETTERS AND SETTERS//////////////////////////////
    public int getType() {
        return type;
    }

}
