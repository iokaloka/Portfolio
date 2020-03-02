package guiltspawn.entity;

import guiltspawn.engine.TileMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Flag extends MapObject {

    private int destinationLevel;
    private int destinationRoom;
    private int spawnPoint;
    private int timer = 0;
    private int col = 0;

    public Flag(TileMap tm, int destinationLevel, int destinationRoom) {
        super(tm);
        this.destinationLevel = destinationLevel;
        this.destinationRoom = destinationRoom;
    }

    public void activate() {
        this.getTileMap().getGame().getHandler().setLoading(true);
        this.getTileMap().getGame().getHandler().setSpawnPoint(spawnPoint);
        this.getTileMap().getGame().getHandler().setCurrentRoom(destinationLevel);
        this.getTileMap().getGame().getHandler().setCurrentRoom(destinationRoom);
        this.getTileMap().getGame().getHandler().setReadyToLoadLevel(true);
    }

    public void tick() {
        timer++;
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        Player p = this.getTileMap().getGame().getHandler().getPlayer();
        if (this.intersects(p)) {
            this.activate();
        }
    }

    public void render(Graphics g) {
        setMapPosition();
        if (timer % 20 == 0) {
            if (col == 0) {
                col = 1;
            } else {
                col = 0;
            }
        }
        if (col == 0) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.YELLOW);
        }

        g.fillRect((int) this.getX() * 32, (int) this.getY() * 32, 32, 32);
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(getRectangle());
    }
    
    @Override
    public boolean intersects(MapObject o){
        Rectangle r1 = this.getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }
    
    @Override
    public Rectangle getRectangle(){
        return new Rectangle((int) this.getX() * 32, (int) this.getY() * 32, 32, 32);
    }

    public int getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(int spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

}
